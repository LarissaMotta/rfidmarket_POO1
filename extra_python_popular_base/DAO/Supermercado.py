import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.DAO.PJuridicaDAO import PJuridicaDAO


class SupermercadoDAO():

	@staticmethod
	def insert(data_base, superm):
		superm.id = PJuridicaDAO.insert(data_base, superm)

		def get_insert_sql(superm):
			cols = ["fk_pessoa", "longitude", "latitude", "unidade"]
			vals = [superm.id, superm.longitude, superm.latitude, superm.unidade]
			return DBCore.format_sql_insert("supermercado", cols, vals, "fk_pessoa")

		try:
			superm.id = data_base.insert(get_insert_sql(superm))
			return superm.id

		except psycopg2.ProgrammingError:
			PJuridicaDAO.remove(data_base, superm.id)
			raise psycopg2.ProgrammingError("Falha ao inserir supermercado na base")

	@staticmethod
	def remove(data_base, superm_id):

		def get_remove_sql(superm_id):
			sql = "DELETE FROM supermercado WHERE fk_pessoa = %d;" %superm_id
			return sql

		data_base.execute(get_remove_sql(superm_id))
		PJuridicaDAO.remove(data_base, superm_id)