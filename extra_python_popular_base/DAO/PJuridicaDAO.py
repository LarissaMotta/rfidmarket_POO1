import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.DAO.PessoaDAO import PessoaDAO


class PJuridicaDAO():

	@staticmethod
	def insert(data_base, pj):
		pj.id = PessoaDAO.insert(data_base, pj)

		def get_insert_sql(pj):
			cols = ["cnpj", "fk_pessoa"]
			vals = [pj.cnpj, pj.id]
			sql = DBCore.format_sql_insert("juridica", cols, vals, "fk_pessoa")
			return sql

		try:
			pj.id = data_base.insert(get_insert_sql(pj))
			return pj.id

		except psycopg2.ProgrammingError:
			PessoaDAO.remove(data_base, pj.id)
			raise psycopg2.ProgrammingError("Falha ao inserir pessoa jurídica na base")

	@staticmethod
	def remove(data_base, pessoa_juridica_id):

		def get_remove_sql(pf_id):
			sql = "DELETE FROM juridica WHERE fk_pessoa = %d;" %pf_id
			return sql

		data_base.execute(get_remove_sql(pessoa_juridica_id))
		PessoaDAO.remove(data_base, pessoa_juridica_id)