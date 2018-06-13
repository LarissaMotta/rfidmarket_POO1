class Cartao():
	titular = str
	bandeira = str
	tipo = str
	validade = str
	numero = str
	id = int

	def __init__(self, titular, bandeira, tipo, validade, numero):
		self.tipo = self.tipo
		self.numero = self.numero
		self.bandeira = self.bandeira
		self.validade = self.validade
		self.titular = self.titular

	def print(self):
		print("TIPO\t\t-> '%s'" % self.tipo)
		print("NÚMERO\t-> %s" % self.numero)
		print("BANDEIRA\t-> '%s'" % self.bandeira)
		print("VALIDADE\t-> %s" % self.validade)
		print("TITULAR\t\t-> '%s'" % self.titular)