import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.DAO.PessoaDAO import PessoaDAO
from extra_python_popular_base.modelo.PJuridica import PJuridica


class PJuridicaDAO():

	__nome_tabela = "juridica"
	__cols = ["cnpj", "fk_pessoa"]

	# Obtém sql única com todos valores formatados;
	@staticmethod
	def get_insert_all_sql(pjuridicas=[PJuridica]):

		cols = PJuridicaDAO.get_nome_colunas()
		nome_tab = PJuridicaDAO.get_nome_tabela()
		sql = "INSERT INTO %s (%s) VALUES" % (nome_tab, (', '.join(cols)))
		sqls_value = []

		# Para cada pessoa, obtenha suas tuplas de valores já formatada;
		for pj in pjuridicas:
			vals = [pj.cnpj, pj.id]
			sqls_value.append(DBCore.format_sql_value(vals))

		sql = sql + '\n' + (",\n".join(sqls_value)) + ';'
		return sql

	@staticmethod
	def insert_n(data_base, pjuridicas=[PJuridica]):

		try:
			pessoas_ids = PessoaDAO.insert_n(data_base, pjuridicas)
			sql_n_pj = PJuridicaDAO.get_insert_all_sql(pjuridicas)
			nome_tab = PJuridicaDAO.get_nome_tabela()
			pjs_ids = data_base.insert_all(sql_n_pj, nome_tab, "fk_pessoa", 1)

			for i in range(len(pjs_ids)):
				pjuridicas[i].id = pjs_ids[i]

			print('\n', sql_n_pj)

			return pjs_ids

		except psycopg2.ProgrammingError:
			raise

	@staticmethod
	def insert(data_base, pj):
		pj.id = PessoaDAO.insert(data_base, pj)

		def get_insert_sql(pj):
			cols = ["cnpj", "fk_pessoa"]
			vals = [pj.cnpj, pj.id]
			sql = DBCore.format_sql_insert("juridica", cols, vals, "fk_pessoa")
			return sql

		try:
			pj.id = data_base.insert(get_insert_sql(pj))
			return pj.id

		except psycopg2.ProgrammingError:
			PessoaDAO.remove(data_base, pj.id)
			raise psycopg2.ProgrammingError("Falha ao inserir pessoa jurídica na base")

	@staticmethod
	def remove(data_base, pessoa_juridica_id):

		def get_remove_sql(pf_id):
			sql = "DELETE FROM juridica WHERE fk_pessoa = %d;" %pf_id
			return sql

		data_base.execute(get_remove_sql(pessoa_juridica_id))
		PessoaDAO.remove(data_base, pessoa_juridica_id)

	@staticmethod
	def get_nome_tabela():
		return PJuridicaDAO.__nome_tabela

	@staticmethod
	def get_nome_colunas():
		return PJuridicaDAO.__cols