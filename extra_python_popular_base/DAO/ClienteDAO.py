import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.DAO.PFisicaDAO import PFisicaDAO
from extra_python_popular_base.modelo.Cliente import Cliente


class ClienteDAO():

	__cols = ["fk_pessoa"]
	__nome_tabela = "cliente"

	@staticmethod
	def insert(data_base, cli=Cliente):
		cli.id = PFisicaDAO.insert(data_base, cli)

		def get_insert_sql(cli=Cliente):
			cols = ["fk_pessoa"]
			vals = [cli.id]
			sql = DBCore.format_sql_insert("cliente", cols, vals, "fk_pessoa")
			return sql

		try:
			cli.id = data_base.insert(get_insert_sql(cli))
			return cli.id

		except psycopg2.Error:
			PFisicaDAO.remove(data_base, cli.id)
			raise psycopg2.ProgrammingError("Falha ao inserir cliente na base")

	# Obtém sql única com todos valores formatados;
	@staticmethod
	def get_insert_all_sql(clientes=[Cliente]):

		cols = ClienteDAO.get_nome_colunas()
		nome_tab = ClienteDAO.get_nome_tabela()
		sql = "INSERT INTO %s (%s) VALUES" % (nome_tab, (', '.join(cols)))
		sqls_value = []

		for cli in clientes:
			vals = [cli.id]
			sqls_value.append(DBCore.format_sql_value(vals))

		sql = sql + '\n' + (",\n".join(sqls_value)) + ';'
		return sql

	@staticmethod
	def insert_n(data_base, clientes=[Cliente]):

		try:
			pfs_ids = PFisicaDAO.insert_n(data_base, clientes)
			sql_n_cli = ClienteDAO.get_insert_all_sql(clientes)
			nome_tab = ClienteDAO.get_nome_tabela()
			clis_ids = data_base.insert_all(sql_n_cli, nome_tab, "fk_pessoa", 0)

			for i in range(len(pfs_ids)):
				clientes[i].id = pfs_ids[i]

			print('\n', sql_n_cli)

			return clis_ids

		except psycopg2.ProgrammingError:
			raise

	@staticmethod
	def remove(data_base, cliente_id):

		def get_remove_sql(cli_id):
			sql = "DELETE FROM cliente WHERE fk_pessoa = %d;" %cli_id
			return sql

		data_base.execute(get_remove_sql(cliente_id))
		PFisicaDAO.remove(data_base, cliente_id)

	@staticmethod
	def get_nome_tabela():
		return ClienteDAO.__nome_tabela

	@staticmethod
	def get_nome_colunas():
		return ClienteDAO.__cols