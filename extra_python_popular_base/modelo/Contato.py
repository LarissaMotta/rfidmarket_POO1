class Contato():

	valor = str
	tipo = str
	id = int
	fk_pessoa = 0

	__default = ("email", "celular", "telefone")

	def __init__(self, valor, tipo):

		if tipo.lower() not in self.__default:
			raise ValueError("Tipo de contato desconhecido!")

		self.valor = valor
		self.tipo = tipo

	def print(self):
		print("%s\t\t-> %s" %(self.tipo, self.valor))
