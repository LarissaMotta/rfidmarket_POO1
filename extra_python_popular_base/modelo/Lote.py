class Lote():

	id = int
	data_compra = str
	fabricacao = str
	validade = str
	quantidade = int
	identificador = str
	fk_produto = int

	def __init__(self, data_compra, fabricacao, validade, quantidade,
	             codigo):
		
		self.data_compra = data_compra
		self.fabricacao = fabricacao
		self.validade = validade
		self.quantidade = quantidade
		self.identificador = codigo

	def print(self):
		print("DATA COMPRA\t\t-> %s" % self.data_compra)
		print("DATA FABRIC.\t-> %s" % self.fabricacao)
		print("DATA VALID.\t\t-> %s" % self.validade)
		print("NÚM. UNIDADES\t-> %d" % self.quantidade)
		print("IDENTIFICADOR\t-> %s" % self.identificador)
