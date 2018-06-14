import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.modelo.Cartao import Cartao


class CartaoDAO():

	__nome_tabela = "cartao"
	__cols = ["nome_titular", "validade", "bandeira", "numero", "tipo"]

	@staticmethod
	def get_insert_all_sql(cartoes=[Cartao]):

		cols = CartaoDAO.get_nome_colunas()
		nome_tabela = CartaoDAO.get_nome_tabela()

		sql = "INSERT INTO %s (%s) VALUES" % (nome_tabela, (', '.join(cols)))
		sqls_value = []

		for c in cartoes:
			vals = [c.titular, c.validade, c.bandeira, c.numero, c.tipo]
			sqls_value.append(DBCore.format_sql_value(vals))

		sql = sql + '\n' + (',\n'.join(sqls_value)) + ';'
		return sql

	@staticmethod
	def insert_n(data_base, cartoes=[Cartao]):

		try:
			sql_n_cartoes = CartaoDAO.get_insert_all_sql(cartoes)
			nome_tab = CartaoDAO.get_nome_tabela()

			print("\n", sql_n_cartoes)

			return data_base.insert_all(sql_n_cartoes, nome_tab, "id", 0)

		except psycopg2.ProgrammingError:
			raise

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

	@staticmethod
	def get_nome_tabela():
		return CartaoDAO.__nome_tabela

	@staticmethod
	def get_nome_colunas():
		return CartaoDAO.__cols