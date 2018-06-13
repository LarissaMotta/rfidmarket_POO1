from extra_python_popular_base.modelo.PJuridica import PJuridica


class Supermercado(PJuridica):

	latitude = float
	longitude = float
	unidade = str
	id = int

	def __init__(self, latitude, longitude, unidade, cnpj, nome, estado, cidade,
				 bairro, rua, numero, cep):
		super().__init__(cnpj, nome, estado, cidade, bairro, rua, numero, cep)
		self.latitude = latitude
		self.longitude = longitude
		self.unidade = unidade

	def print(self):
		super().print()
		print("\nLOCALIZAÇÃO:")
		print("LATITUDE\t-> %.6f" % self.latitude)
		print("LONGITUDE\t-> %.6f" % self.longitude)
		print("UNIDADE\t\t-> '%s'" % self.unidade)
