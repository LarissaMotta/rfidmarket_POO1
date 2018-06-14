import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.DAO.PJuridicaDAO import PJuridicaDAO
from extra_python_popular_base.modelo.Supermercado import Supermercado


class SupermercadoDAO():

	__cols = ["fk_pessoa", "longitude", "latitude", "unidade"]
	__nome_tabela = "supermercado"

	# Obtém sql única com todos valores formatados;
	@staticmethod
	def get_insert_all_sql(superms=[Supermercado]):

		cols = SupermercadoDAO.get_nome_colunas()
		nome_tab = SupermercadoDAO.get_nome_tabela()
		sql = "INSERT INTO %s (%s) VALUES" % (nome_tab, (', '.join(cols)))
		sqls_value = []

		for superm in superms:
			vals = [superm.id, superm.longitude, superm.latitude, superm.unidade]
			sqls_value.append(DBCore.format_sql_value(vals))

		sql = sql + '\n' + (',\n'.join(sqls_value)) + ';'
		return sql

	@staticmethod
	def insert_n(data_base, superms=[Supermercado]):

		try:
			pjs_ids = PJuridicaDAO.insert_n(data_base, superms)

			for i in range(len(pjs_ids)):
				superms[i].id = pjs_ids[i]

			sql_n_superms = SupermercadoDAO.get_insert_all_sql(superms)
			nome_tab = SupermercadoDAO.get_nome_tabela()
			superms_ids = data_base.insert_all(sql_n_superms, nome_tab, "fk_pessoa", 0)

			print('\n', sql_n_superms)

			return superms_ids

		except psycopg2.ProgrammingError:
			raise

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

		return 0

	@staticmethod
	def get_nome_tabela():
		return SupermercadoDAO.__nome_tabela

	@staticmethod
	def get_nome_colunas():
		return SupermercadoDAO.__cols