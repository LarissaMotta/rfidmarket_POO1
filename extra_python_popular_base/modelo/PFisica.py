from extra_python_popular_base.modelo.Pessoa import Pessoa

class PFisica(Pessoa):

	data_nasc = str
	login = str
	cpf = str
	senha = str
	genero = str
	id = int

	def __init__(self, data_nasc, login, cpf, senha, genero,
                 nome, cep, numero, rua, bairro, cidade, estado):
		super().__init__(nome, estado, cidade, bairro, rua, numero, cep)
		self.senha = senha
		self.data_nasc = data_nasc
		self.login = login
		self.genero = genero
		self.cpf = cpf

	def print(self):
		super().print()
		print("\nGENERO\t\t-> '%s'" % self.genero)
		print("DATA NSC\t-> '%s'" % self.data_nasc)
		print("LOGIN\t\t-> '%s'" % self.login)
		print("SENHA\t\t-> '%s'" % self.senha)
		print("CPF\t\t\t-> '%s'" % self.cpf)
