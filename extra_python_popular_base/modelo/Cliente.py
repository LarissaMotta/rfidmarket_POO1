from extra_python_popular_base.modelo.PFisica import PFisica

class Cliente(PFisica):

	id = int

	def __init__(self, data_nasc, login, cpf, senha, genero,
                 nome, cep, numero, rua, bairro, cidade, estado):
		super().__init__(data_nasc, login, cpf, senha, genero,
                 nome, cep, numero, rua, bairro, cidade, estado)