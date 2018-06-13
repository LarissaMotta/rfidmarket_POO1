class Pessoa(object):

	nome = str
	estado = str
	cidade = str
	bairro = str
	rua = str
	numero = str
	cep = str
	id = int
	contatos = []

	def __init__(self, nome, estado, cidade, bairro, rua, numero, cep):
		self.nome = nome
		self.numero = numero
		self.rua = rua
		self.bairro = bairro
		self.cidade = cidade
		self.estado = estado
		self.cep = cep

	def print(self):
		print("NOME\t-> '%s'" % self.nome)
		print("ESTADO\t-> '%s'" % self.estado)
		print("CIDADE\t-> '%s'" % self.cidade)
		print("BAIRRO\t-> '%s'" % self.bairro)
		print("RUA\t\t-> '%s'" % self.rua)
		print("NUMERO\t-> %s" % self.numero)
		print("CEP\t\t-> %s" % self.cep)

		if self.contatos:
			print("\nCONTATOS:")

			for contato in self.contatos: contato.print()
