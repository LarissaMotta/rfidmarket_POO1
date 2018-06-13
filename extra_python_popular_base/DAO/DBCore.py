import psycopg2

class DBCore():
	__database_nome = ""
	__usuario = ""
	__senha = ""
	__host = ""
	__conexao = None
	__ponteiro = None

	def __init__(self, database_nome, usuario, senha, host="localhost"):
		self.__database_nome = database_nome
		self.__usuario = usuario
		self.__senha = senha
		self.__host = host

	def __rollback(self):
		self.__ponteiro.execute("ROLLBACK;")

	def execute(self, sql):

		try:
			self.__ponteiro.execute(sql)

		except psycopg2.IntegrityError:
			self.__rollback()
			raise

		finally:
			self.__conexao.commit()

	def connected(self):
		return self.__conexao == None

	def connect(self):
		self.__conexao = psycopg2.connect(host=self.__host, database=self.__database_nome,
										  user=self.__usuario, password=self.__senha)
		self.__ponteiro = self.__conexao.cursor()

	def insert(self, sql):
		self.execute(sql)
		id = self.__ponteiro.fetchone()[0]
		return id

	@staticmethod
	def format_sql_insert(nome_tabela, nome_colunas, valores, nome_col_retorno):

		cols = nome_colunas
		col_ret = nome_col_retorno
		sql_value = DBCore.format_sql_value(valores)

		sql = "INSERT INTO %s(%s) VALUES%s RETURNING %s;" %\
		      (nome_tabela, (', '.join(cols)), sql_value, col_ret)

		return sql

	@staticmethod
	def format_sql_value(valores):

		vals_str = []

		for valor in valores:

			if (not valor or valor == None):
				vals_str.append("null")

			elif (type(valor) in [int, float]):
				vals_str.append(str(valor))

			elif (type(valor) == str and "'" not in valor):
				vals_str.append("'%s'" % valor if type(valor) == str else str(valor))

			elif (type(valor) == str and "'" in valor):
				vals_str.append("$txt$%s$txt$" % valor)

			else:
				raise ValueError("Tipo inválido, somente float, int e str aceitos")

		sql = "(%s)" %(', '.join(vals_str))

		return sql

	def select(self, sql):
		self.__ponteiro.execute(sql)
		return self.__ponteiro.fetchall()

	def create(self, sql):
		self.execute(sql)

	def drop(self, nome_tabela):
		self.execute("drop table if exists " + nome_tabela.strip())

	def drop_cascade(self, nome_tabela):
		self.drop(nome_tabela+" cascade;")

	def drop_all(self):
		nome_tabelas = self.select("SELECT table_name FROM information_schema.tables "
		               "WHERE table_schema='public' AND table_type='BASE TABLE';")

		for nome in nome_tabelas:
			self.drop_cascade(nome[0])

	def delete_all(self, nome_tabela):
		self.execute("delete from " + nome_tabela.strip())

	def close(self):
		self.__conexao.close()
