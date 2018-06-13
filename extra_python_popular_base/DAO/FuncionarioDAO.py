import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.DAO.PFisicaDAO import PFisicaDAO
from extra_python_popular_base.modelo.Funcionario import Funcionario


class FuncionarioDAO():

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