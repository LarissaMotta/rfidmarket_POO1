from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.modelo.Cartao import Cartao


class CartaoDAO():

	@staticmethod
	def insert(data_base, cartao=Cartao):

		def get_insert_sql(c=Cartao):
			cols = ["nome_titular", "validade", "bandeira", "numero", "tipo"]
			vals = [c.titular, c.validade, c.bandeira, c.numero, c.tipo]
			sql = DBCore.format_sql_insert("cartao", cols, vals, "id")
			return sql

		cartao.id = data_base.insert(get_insert_sql(cartao))
		return cartao.id

	@staticmethod
	def remove(data_base, cartao_id):

		def get_remove_sql(c_id):
			sql = "DELETE FROM cartao WHERE id = %d;" %c_id
			return sql

		data_base.execute(get_remove_sql(cartao_id))