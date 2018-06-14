import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.DAO.PFisicaDAO import PFisicaDAO
from extra_python_popular_base.modelo.Funcionario import Funcionario


class FuncionarioDAO():

	__nome_tabela = "funcionario"
	__cols = ["fk_pessoa", "cargo", "setor"]


	# Obtém sql única com todos valores formatados;
	@staticmethod
	def get_insert_all_sql(funcionarios=[Funcionario]):

		cols = FuncionarioDAO.get_nome_colunas()
		nome_tab = FuncionarioDAO.get_nome_tabela()
		sql = "INSERT INTO %s (%s) VALUES" % (nome_tab, (', '.join(cols)))
		sqls_value = []

		for f in funcionarios:
			vals = [f.id, f.cargo, f.setor]
			sqls_value.append(DBCore.format_sql_value(vals))

		sql = sql + '\n' + (",\n".join(sqls_value)) + ';'
		return sql

	@staticmethod
	def insert_n(data_base, funcionarios=[Funcionario]):

		try:
			pfs_ids = PFisicaDAO.insert_n(data_base, funcionarios)
			sql_n_func = FuncionarioDAO.get_insert_all_sql(funcionarios)
			nome_tab = FuncionarioDAO.get_nome_tabela()
			func_ids = data_base.insert_all(sql_n_func, nome_tab, "fk_pessoa", 0)

			for i in range(len(pfs_ids)):
				funcionarios[i].id = pfs_ids[i]

			print('\n', sql_n_func)

			return func_ids

		except psycopg2.ProgrammingError:
			raise

	@staticmethod
	def insert(data_base, func=Funcionario):
		func.id = PFisicaDAO.insert(data_base, func)

		def get_insert_sql(func):
			cols = ["fk_pessoa", "cargo", "setor"]
			vals = [func.id, func.cargo, func.setor]
			sql = DBCore.format_sql_insert("funcionario", cols, vals, "fk_pessoa")
			return sql

		try:
			func.id = data_base.insert(get_insert_sql(func))
			return func.id

		except psycopg2.ProgrammingError:
			PFisicaDAO.remove(data_base, func.id)
			raise psycopg2.ProgrammingError("Falha ao inserir funcionário na base")

	@staticmethod
	def remove(data_base, funcionario_id):

		def get_remove_sql(func_id):
			sql = "DELETE FROM funcionario WHERE fk_pessoa = %d;" %func_id
			return sql

		data_base.execute(get_remove_sql(funcionario_id))
		PFisicaDAO.remove(data_base, funcionario_id)

	@staticmethod
	def get_nome_tabela():
		return FuncionarioDAO.__nome_tabela

	@staticmethod
	def get_nome_colunas():
		return FuncionarioDAO.__cols