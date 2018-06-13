class Produto():

	nome = str
	preco = float
	codigo = str
	descricao = str
	custo = float
	estoque = int
	tipo = str
	quant_prateleira = int
	marca = str
	lotes = []
	id = int

	#CODIGO PADRÃO UPC

	def __init__(self, nome, preco, codigo, descricao, custo, estoque,
	             tipo, quant_prateleira, marca):
		self.nome = nome
		self.preco = preco
		self.codigo = codigo
		self.descricao = descricao
		self.custo = custo
		self.estoque = estoque
		self.tipo = tipo
		self.quant_prateleira = quant_prateleira
		self.marca = marca

	def addLote(self, lote):
		self.lotes.append(lote)

	def print(self):
		print("NOME\t\t-> '%s'" % self.nome)
		print("PREÇO\t\t-> R$%.2f" % self.preco)
		print("CÓDIGO\t\t-> %s" % self.codigo)
		print("DESCRIÇÃO\t-> '%s'" % self.descricao)
		print("CUSTO\t\t-> R$%.2f" % self.custo)
		print("TIPO\t\t-> %s" % self.tipo)
		print("QTD. PRAT.\t-> %d" % self.quant_prateleira)
		print("MARCA\t\t-> %s" % self.marca)

		if (self.lotes):
			print("\nLOTES:")

			for i in range(len(self.lotes)):
				print("\nNÚMERO:\t\t\t-> %d" %(i + 1))
				self.lotes[i].print()