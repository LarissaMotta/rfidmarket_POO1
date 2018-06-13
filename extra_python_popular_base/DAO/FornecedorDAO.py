import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.DAO.PJuridicaDAO import PJuridicaDAO


class FornecedorDAO():

	@staticmethod
	def insert(data_base, forn):
		forn.id = PJuridicaDAO.insert(data_base, forn)

		def get_insert_sql(forn):
			cols = ["fk_pessoa"]
			vals = [forn.id]
			sql = DBCore.format_sql_insert("fornecedor", cols, vals, "fk_pessoa")
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