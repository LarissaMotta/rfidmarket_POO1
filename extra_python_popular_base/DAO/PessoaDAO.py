import psycopg2

from extra_python_popular_base.DAO.ContatoDAO import ContatoDAO
from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.modelo.Pessoa import Pessoa


class PessoaDAO():

	@staticmethod
	def insert(data_base, pessoa):

		def get_insert_sql(pessoa):
			p = pessoa
			cols = ["nome", "numero", "rua", "cep", "bairro", "cidade", "estado"]
			vals = [p.nome, p.numero, p.rua, p.cep, p.bairro, p.cidade, p.estado]
			return DBCore.format_sql_insert("pessoa", cols, vals, "id")

		try:
			pessoa.id = data_base.insert(get_insert_sql(pessoa))

			for contato in pessoa.contatos:
				contato.id = ContatoDAO.insert(data_base, pessoa.id, contato)

			return pessoa.id

		except psycopg2.ProgrammingError:
			raise #psycopg2.ProgrammingError("Falha ao inserir pessoa na base")

	@staticmethod
	def get_sql_insert_n(data_base, pessoas=[Pessoa]):

		def get_insert_sql(pessoas):

			cols = ["nome", "numero", "rua", "cep", "bairro", "cidade", "estado"]
			sql = "INSERT INTO pessoa (%s) VALUES" %(''.join(cols))
			sqls_value = []

			for p in pessoas:
				vals = [p.nome, p.numero, p.rua, p.cep, p.bairro, p.cidade, p.estado]
				sqls_value.append(DBCore.format_sql_value(vals))

			sql = sql + (', '.join(sqls_value)) + ';'
			return sql

		try:

			for pessoa in pessoas:
			pessoa.id = data_base.insert(get_insert_sql(pessoa))

			for contato in pessoa.contatos:
				contato.id = ContatoDAO.insert(data_base, pessoa.id, contato)

			return pessoa.id

		except psycopg2.ProgrammingError:
			raise #psycopg2.ProgrammingError("Falha ao inserir pessoa na base")


	@staticmethod
	def remove(data_base, pessoa_id):

		def get_remove_sql(p_id):
			sql = "DELETE FROM pessoa WHERE id = %d;" %p_id
			return sql

		ContatoDAO.remove(data_base, pessoa_id)
		data_base.execute(get_remove_sql(pessoa_id))
		return pessoa_id
