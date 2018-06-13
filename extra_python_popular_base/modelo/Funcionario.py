from extra_python_popular_base.modelo.PFisica import PFisica


class Funcionario(PFisica):

	cargo = str
	setor = str
	id = int

	def __init__(self, cargo, setor, data_nasc, login, cpf, senha, genero,
				 nome, cep, numero, rua, bairro, cidade, estado):
		super().__init__(data_nasc, login, cpf, senha, genero,
				 nome, cep, numero, rua, bairro, cidade, estado)
		self.cargo = cargo
		self.setor = setor

	def print(self):
		super().print()
		print("\nPROFISSÃO")
		print("SETOR:\t%s" % self.setor)
		print("CARGO:\t%s" % self.cargo)