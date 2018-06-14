import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.DAO.PessoaDAO import PessoaDAO
from extra_python_popular_base.modelo.PFisica import PFisica


class PFisicaDAO():

	__cols = ["data_nasc", "cpf", "senha", "fk_pessoa", "genero", "login"]
	__nome_tabela = "fisica"

	# Obtém sql única com todos valores formatados;
	@staticmethod
	def get_insert_all_sql(pfisicas=[PFisica]):

		cols = PFisicaDAO.get_nome_colunas()
		nome_tab = PFisicaDAO.get_nome_tabela()
		sql = "INSERT INTO %s (%s) VALUES" % (nome_tab, (', '.join(cols)))
		sqls_value = []

		# Para cada pessoa, obtenha suas tuplas de valores já formatada;
		for pf in pfisicas:
			vals = [pf.data_nasc, pf.cpf, pf.senha, pf.id, pf.genero, pf.login]
			sqls_value.append(DBCore.format_sql_value(vals))

		sql = sql + '\n' + (',\n'.join(sqls_value)) + ';'
		return sql

	@staticmethod
	def insert_n(data_base, pfisicas=[PFisica]):

		try:
			pessoas_ids = PessoaDAO.insert_n(data_base, pfisicas)
			sql_n_pf = PFisicaDAO.get_insert_all_sql(pfisicas)
			nome_tab = PFisicaDAO.get_nome_tabela()
			pfs_ids = data_base.insert_all(sql_n_pf, nome_tab, "fk_pessoa", 3)

			for i in range(len(pfs_ids)):
				pfisicas[i].id = pfs_ids[i]

			print('\n', sql_n_pf)

			return pfs_ids

		except psycopg2.ProgrammingError:
			raise

	@staticmethod
	def insert(data_base, pf=PFisica):
		pf.id = PessoaDAO.insert(data_base, pf)

		def get_insert_sql(pf=PFisica):
			cols = ["data_nasc", "cpf", "senha", "fk_pessoa", "genero", "login"]
			vals = [pf.data_nasc, pf.cpf, pf.senha, pf.id, pf.genero, pf.login]
			sql = DBCore.format_sql_insert("fisica", cols, vals, "fk_pessoa")
			return sql

		try:
			pf.id = data_base.insert(get_insert_sql(pf))
			return pf.id

		except psycopg2.ProgrammingError:
			PessoaDAO.remove(data_base, pf.id)
			raise psycopg2.ProgrammingError("Falha ao inserir pessoa física na base")

	@staticmethod
	def remove(data_base, pessoa_fisica_id):

		def get_remove_sql(pf_id):
			sql = "DELETE FROM fisica WHERE fk_pessoa = %d;" %pf_id
			return sql

		data_base.execute(get_remove_sql(pessoa_fisica_id))
		PessoaDAO.remove(data_base, pessoa_fisica_id)

	@staticmethod
	def get_nome_tabela():
		return PFisicaDAO.__nome_tabela

	@staticmethod
	def get_nome_colunas():
		return PFisicaDAO.__cols