import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.modelo.Lote import Lote


class LoteDAO():

	@staticmethod
	def insert(data_base, fk_produto, lote=Lote):

		def get_insert_sql(lote):
			cols = ["data_compra", "fabricacao", "validade", "quantidade",
			        "identificador", "fk_produto"]
			vals = [lote.data_compra, lote.fabricacao, lote.validade, lote.quantidade,
			        lote.identificador,fk_produto]
			return DBCore.format_sql_insert("lote", cols, vals, "id")

		try:
			lote.id = data_base.insert(get_insert_sql(lote))
			return lote.id

		except psycopg2.ProgrammingError:
			raise psycopg2.ProgrammingError("Falha ao inserir lote na base")

	@staticmethod
	def remove(data_base, id_lote):

		def get_remove_sql(l_id):
			return "DELETE FROM lote WHERE id = %d;" %l_id

		data_base.execute(get_remove_sql(id_lote))
		return id_lote
