import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore


class ContatoDAO():

	@staticmethod
	def insert(data_base, fk_pessoa, contato):

		def get_insert_sql(cont):
			cols = ["descricao", "tipo", "fk_pessoa"]
			vals = [cont.valor, cont.tipo, fk_pessoa]
			sql = DBCore.format_sql_insert("contato", cols, vals, "id")
			return sql

		try:
			contato.id = data_base.insert(get_insert_sql(contato))
			return contato.id

		except psycopg2.ProgrammingError:
			raise psycopg2.ProgrammingError("Falha ao inserir contato na base")

	@staticmethod
	def remove(data_base, fk_pessoa):

		def get_remove_sql(p_id):
			sql = "DELETE FROM contato WHERE fk_pessoa = %d;" %p_id
			return sql

		data_base.execute(get_remove_sql(fk_pessoa))
		return fk_pessoa
