import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.modelo.Contato import Contato


class ContatoDAO():

	__cols = ["descricao", "tipo", "fk_pessoa"]
	__nome_tabela = "contato"

	@staticmethod
	def insert(data_base, fk_pessoa, contato):

		def get_insert_sql(cont):
			cols = ContatoDAO.__cols
			vals = [cont.valor, cont.tipo, fk_pessoa]
			sql = DBCore.format_sql_insert("contato", cols, vals, "id")
			return sql

		try:
			contato.id = data_base.insert(get_insert_sql(contato))
			return contato.id

		except psycopg2.ProgrammingError:
			raise psycopg2.ProgrammingError("Falha ao inserir contato na base")

	@staticmethod
	def get_insert_all_sql(contatos=[Contato]):

		cols = ContatoDAO.get_nome_colunas()
		nome_tabela = ContatoDAO.get_nome_tabela()

		sql = "INSERT INTO %s (%s) VALUES" % (nome_tabela, (', '.join(cols)))
		sqls_value = []

		# Para cada pessoa, obtenha suas tuplas de valores já formatada;
		for c in contatos:
			vals = [c.valor, c.tipo, c.fk_pessoa]
			sqls_value.append(DBCore.format_sql_value(vals))

		sql = sql + '\n' + (',\n'.join(sqls_value)) + ';'
		return sql

	@staticmethod
	def insert_n(data_base, contatos=[Contato]):

		try:
			sql_n_contatos = ContatoDAO.get_insert_all_sql(contatos)
			nome_tab = ContatoDAO.get_nome_tabela()
			print("\n", sql_n_contatos)
			return data_base.insert_all(sql_n_contatos, nome_tab, "id", 0)

		except psycopg2.ProgrammingError:
			raise

	@staticmethod
	def remove(data_base, fk_pessoa):

		def get_remove_sql(p_id):
			sql = "DELETE FROM contato WHERE fk_pessoa = %d;" %p_id
			return sql

		data_base.execute(get_remove_sql(fk_pessoa))
		return fk_pessoa

	@staticmethod
	def get_nome_tabela():
		return ContatoDAO.__nome_tabela

	@staticmethod
	def get_nome_colunas():
		return ContatoDAO.__cols