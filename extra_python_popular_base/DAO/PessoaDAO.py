import psycopg2

from extra_python_popular_base.DAO.ContatoDAO import ContatoDAO
from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.modelo.Contato import Contato
from extra_python_popular_base.modelo.Pessoa import Pessoa


class PessoaDAO():

	__cols = ["nome", "numero", "rua", "cep", "bairro", "cidade", "estado"]
	__nome_tabela = "pessoa"

	@staticmethod
	def insert(data_base, pessoa):

		def get_insert_sql(pessoa):
			p = pessoa
			cols = PessoaDAO.__cols
			vals = [p.nome, p.numero, p.rua, p.cep, p.bairro, p.cidade, p.estado]
			return DBCore.format_sql_insert(PessoaDAO.__nome_tabela, cols, vals, "id")

		try:
			pessoa.id = data_base.insert(get_insert_sql(pessoa))

			for contato in pessoa.contatos:
				contato.id = ContatoDAO.insert(data_base, pessoa.id, contato)

			return pessoa.id

		except psycopg2.ProgrammingError:
			raise #psycopg2.ProgrammingError("Falha ao inserir pessoa na base")

	# Obtém sql única com todos valores formatados;
	@staticmethod
	def get_insert_all_sql(pessoas=[Pessoa]):

		cols = PessoaDAO.get_nome_colunas()
		nome_tab = PessoaDAO.get_nome_tabela()
		sql = "INSERT INTO %s (%s) VALUES" % (nome_tab, (', '.join(cols)))
		sqls_value = []

		# Para cada pessoa, obtenha suas tuplas de valores já formatada;
		for p in pessoas:
			vals = [p.nome, p.numero, p.rua, p.cep, p.bairro, p.cidade, p.estado]
			sqls_value.append(DBCore.format_sql_value(vals))

		sql = sql + '\n' + (',\n'.join(sqls_value)) + ';'
		return sql

	@staticmethod
	def insert_n(data_base, pessoas=[Pessoa]):

		try:
			sql_n_pessoas = PessoaDAO.get_insert_all_sql(pessoas)
			nome_tab = PessoaDAO.get_nome_tabela()
			all_id = data_base.insert_all(sql_n_pessoas, nome_tab, "id", 1)

			for i in range(len(all_id)):
				pessoas[i].id = all_id[i]

			print('\n', sql_n_pessoas)
			contatos = []

			for p in pessoas:

				for c in p.contatos:
					c.fk_pessoa = p.id
					contatos.append(c)

			ContatoDAO.insert_n(data_base, contatos)

			return all_id

		except psycopg2.ProgrammingError:
			raise

	@staticmethod
	def remove(data_base, pessoa_id):

		def get_remove_sql(p_id):
			sql = "DELETE FROM %s WHERE id = %d;" %(PessoaDAO.__nome_tabela, p_id)
			return sql

		ContatoDAO.remove(data_base, pessoa_id)
		data_base.execute(get_remove_sql(pessoa_id))
		return pessoa_id

	@staticmethod
	def get_nome_tabela():
		return PessoaDAO.__nome_tabela

	@staticmethod
	def get_nome_colunas():
		return PessoaDAO.__cols