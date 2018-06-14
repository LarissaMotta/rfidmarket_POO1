import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.DAO.PJuridicaDAO import PJuridicaDAO
from extra_python_popular_base.modelo.Fornecedor import Fornecedor


class FornecedorDAO():

	__cols = ["fk_pessoa"]
	__nome_tabela = "fornecedor"

	# Obtém sql única com todos valores formatados;
	@staticmethod
	def get_insert_all_sql(fornecedores=[Fornecedor]):

		cols = FornecedorDAO.get_nome_colunas()
		nome_tab = FornecedorDAO.get_nome_tabela()
		sql = "INSERT INTO %s (%s) VALUES" % (nome_tab, (', '.join(cols)))
		sqls_value = []

		for f in fornecedores:
			vals = [f.id]
			sqls_value.append(DBCore.format_sql_value(vals))

		sql = sql + '\n' + (',\n'.join(sqls_value)) + ';'
		return sql

	@staticmethod
	def insert_n(data_base, fornecedores=[Fornecedor]):

		try:
			pjs_ids = PJuridicaDAO.insert_n(data_base, fornecedores)

			for i in range(len(pjs_ids)):
				fornecedores[i].id = pjs_ids[i]

			sql_n_forn = FornecedorDAO.get_insert_all_sql(fornecedores)
			nome_tab = FornecedorDAO.get_nome_tabela()
			forn_ids = data_base.insert_all(sql_n_forn, nome_tab, "fk_pessoa", 0)

			print('\n', sql_n_forn)

			return forn_ids

		except psycopg2.ProgrammingError:
			raise

	@staticmethod
	def insert(data_base, forn):
		forn.id = PJuridicaDAO.insert(data_base, forn)

		def get_insert_sql(forn):
			cols = FornecedorDAO.get_nome_colunas()
			nome_tab = FornecedorDAO.get_nome_tabela()
			vals = [forn.id]
			sql = DBCore.format_sql_insert(nome_tab, cols, vals, "fk_pessoa")
			return sql

		try:
			forn.id = data_base.insert(get_insert_sql(forn))
			return forn.id

		except psycopg2.ProgrammingError:
			PJuridicaDAO.remove(data_base, forn.id)
			raise psycopg2.ProgrammingError("Falha ao inserir fornecedor na base")

	@staticmethod
	def remove(data_base, fornecedor_id):

		def get_remove_sql(forn_id):
			sql = "DELETE FROM fornecedor WHERE fk_pessoa = %d;" %forn_id
			return sql

		data_base.execute(get_remove_sql(fornecedor_id))
		PJuridicaDAO.remove(data_base, fornecedor_id)

	@staticmethod
	def get_nome_tabela():
		return FornecedorDAO.__nome_tabela

	@staticmethod
	def get_nome_colunas():
		return FornecedorDAO.__cols