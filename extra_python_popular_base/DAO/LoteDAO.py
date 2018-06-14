import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.modelo.Lote import Lote


class LoteDAO():

	__cols = ["data_compra", "fabricacao", "validade", "quantidade", "identificador",
	        "fk_produto"]
	__nome_tabela = "lote"

	@staticmethod
	def get_insert_all_sql(lotes=[Lote]):

		cols = LoteDAO.get_nome_colunas()
		nome_tabela = LoteDAO.get_nome_tabela()

		sql = "INSERT INTO %s (%s) VALUES" % (nome_tabela, (', '.join(cols)))
		sqls_value = []

		# Para cada pessoa, obtenha suas tuplas de valores já formatada;
		for l in lotes:
			vals = [l.data_compra, l.fabricacao, l.validade, l.quantidade,
			        l.identificador, l.fk_produto]
			sqls_value.append(DBCore.format_sql_value(vals))

		sql = sql + '\n' + (',\n'.join(sqls_value)) + ';'
		return sql

	@staticmethod
	def insert_n(data_base, lotes=[Lote]):

		try:
			sql_n_lotes = LoteDAO.get_insert_all_sql(lotes)
			nome_tab = LoteDAO.get_nome_tabela()
			print("\n", sql_n_lotes)
			return data_base.insert_all(sql_n_lotes, nome_tab, "id", 0)

		except psycopg2.ProgrammingError:
			raise

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
		return 0

	@staticmethod
	def get_nome_tabela():
		return LoteDAO.__nome_tabela

	@staticmethod
	def get_nome_colunas():
		return LoteDAO.__cols