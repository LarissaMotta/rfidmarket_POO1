import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.DAO.PessoaDAO import PessoaDAO
from extra_python_popular_base.modelo.PFisica import PFisica


class PFisicaDAO():

	@staticmethod
	def insert(data_base, pf=PFisica):
		pf.id = PessoaDAO.insert(data_base, pf)

		def get_insert_sql(pf=PFisica):
			cols = ["data_nasc", "cpf", "senha", "fk_pessoa", "genero", "login"]
			vals = [pf.data_nasc, pf.cpf, pf.senha, pf.id, pf.genero, pf.login]
			sql = DBCore.format_sql_insert("fisica", cols, vals, "fk_pessoa")
			return sql

		try:
			pf.id = data_base.insert(get_insert_sql(pf))
			return pf.id

		except psycopg2.ProgrammingError:
			PessoaDAO.remove(data_base, pf.id)
			raise psycopg2.ProgrammingError("Falha ao inserir pessoa física na base")

	@staticmethod
	def remove(data_base, pessoa_fisica_id):

		def get_remove_sql(pf_id):
			sql = "DELETE FROM fisica WHERE fk_pessoa = %d;" %pf_id
			return sql

		data_base.execute(get_remove_sql(pessoa_fisica_id))
		PessoaDAO.remove(data_base, pessoa_fisica_id)