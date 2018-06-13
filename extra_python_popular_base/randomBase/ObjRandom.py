from os import path
from datetime import datetime
from random import randint, choice, uniform

from extra_python_popular_base.modelo.Cartao import Cartao
from extra_python_popular_base.modelo.Cliente import Cliente
from extra_python_popular_base.modelo.Contato import Contato
from extra_python_popular_base.modelo.Fornecedor import Fornecedor
from extra_python_popular_base.modelo.Funcionario import Funcionario
from extra_python_popular_base.modelo.Lote import Lote
from extra_python_popular_base.modelo.PFisica import PFisica
from extra_python_popular_base.modelo.PJuridica import PJuridica
from extra_python_popular_base.modelo.Pessoa import Pessoa
from extra_python_popular_base.modelo.Produto import Produto
from extra_python_popular_base.modelo.Supermercado import Supermercado
from extra_python_popular_base.randomBase.Util import Util

# Caminho dos arquivos de dados usados para criar os objetos randômicos;
dir_corrente = path.dirname(path.abspath(__file__))
path_cartao_bandeiras = path.join(dir_corrente, "cartao_bandeiras.txt")
path_contato_ddd = path.join(dir_corrente, "contato_ddd.txt")
path_contato_cel_tel = path.join(dir_corrente, "contato_cel_tel.csv")
path_fisica_nome = path.join(dir_corrente, "pessoas_fisicas_nomes.txt")
path_fisica_nome_sexo = path.join(dir_corrente, "nome_sexo.csv")
path_fornecedor_nome = path.join(dir_corrente, "fornecedores_nomes.txt")
path_pessoa_cidades = path.join(dir_corrente, "endereco_cidades.txt")
path_pessoa_estados = path.join(dir_corrente, "endereco_estados_siglas.txt")
path_pessoa_bairros = path.join(dir_corrente, "endereco_bairros.txt")
path_pessoa_ruas = path.join(dir_corrente, "endereco_ruas.txt")
path_produtos = path.join(dir_corrente, "produtos.txt")
path_supermercado_nome = path.join(dir_corrente, "supermercado_nomes.txt")

# Classes 'abstratas', bases;
class _PessoaRandom(Pessoa):

	__estados = ["AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS",
	           "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC",
	           "SP", "SE", "TO"]

	def __init__(self, nome):
		self.nome = nome
		self.numero = randint(1, 2000)

		# Quantidade de contatos que essa pessoa pode ter (de 1 a 3);
		_qtd = randint(1, 3)
		_tipos = ["telefone", "celular"]

		self.contatos = [ContatoRandom(self.nome, choice(_tipos)) for i in range(_qtd)]
		self.rua = Util.get_linha_random(path_pessoa_ruas).strip()
		self.bairro = Util.get_linha_random(path_pessoa_bairros).strip()
		self.cidade = Util.get_linha_random(path_pessoa_cidades).strip()
		self.estado = choice(self.__estados)
		self.cep = self.get_cep_random()

		Pessoa.__init__(self, self.nome, self.estado, self.cidade, self.bairro, self.rua, self.numero, self.cep)

	@staticmethod
	def get_cep_random():
		return "29%s-%s" % (
			"".join([str(randint(0, 9)) for i in range(3)]), "".join([str(randint(0, 9)) for i in range(3)]))


class _FisicaRandom(_PessoaRandom, PFisica):

	def __init__(self, nome="", sexo=''):

		if (not nome or not sexo or sexo not in ['f', 'F', 'm', 'M']):
			nome_sexo = Util.get_linha_random(path_fisica_nome_sexo).split(';')
			nome = nome_sexo[0]
			sexo = nome_sexo[1]

		_PessoaRandom.__init__(self, nome)

		self.senha = self.get_senha_random()
		self.data_nasc = Util.get_data_random()
		self.login = Util.get_email_random(nome)
		self.contatos.append(Contato(self.login, "email"))

		self.genero = sexo.upper()
		self.cpf = self.get_cpf_random()
		PFisica.__init__(self, self.data_nasc, self.login, self.cpf, self.senha, self.genero,
		                 self.nome, self.cep, self.numero, self.rua, self.bairro,
		                 self.cidade, self.estado)

	@staticmethod
	def get_senha_random():
		tamanho = randint(6, 12)
		chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
		return "".join([choice(chars) for i in range(tamanho)])

	@staticmethod
	def get_cpf_random():

		numeros = [randint(0, 9) for i in range(9)]

		# calcula digito 1 e acrescenta ao numero
		soma = sum(x * y for x, y in zip(numeros, range(10, 1, -1)))
		digito1 = 11 - soma % 11

		if digito1 >= 10: digito1 = 0

		numeros.append(digito1)

		# calcula digito 2 e acrescenta ao numero
		soma = sum(x * y for x, y in zip(numeros, range(11, 1, -1)))
		digito2 = 11 - soma % 11

		if digito2 >= 10: digito2 = 0

		numeros.append(digito2)

		return "%d%d%d.%d%d%d.%d%d%d-%d%d" % tuple(numeros)

	def print(self):
		super().print()


class _JuridicaRandom(_PessoaRandom, PJuridica):

	def __init__(self, nome):
		_PessoaRandom.__init__(self, nome)

		self.cnpj = self.get_cnpj_random()
		PJuridica.__init__(self, self.cnpj, self.nome, self.estado, self.cidade,
		                   self.bairro, self.rua, self.numero, self.cep)

	@staticmethod
	def get_cnpj_random():

		def calculate_special_digit(l):
			digit = 0

			for i, v in enumerate(l):
				digit += v * (i % 8 + 2)

			digit = 11 - digit % 11

			return digit if digit < 10 else 0

		cnpj = [1, 0, 0, 0] + [randint(0, 9) for x in range(8)]

		for _ in range(2):
			cnpj = [calculate_special_digit(cnpj)] + cnpj

		return '%s%s.%s%s%s.%s%s%s/%s%s%s%s-%s%s' % tuple(cnpj[::-1])


# Classes concretas, de fato irão preencher a base de testes;
class CartaoRandom(Cartao):

	def __init__(self, ano_inicio=datetime.now().year - 3, ano_fim=datetime.now().year + 3):
		self.tipo = self._get_tipo_random()
		self.numero = self._get_numero_str_random()
		self.bandeira = self._get_bandeira_random()
		self.validade = self._get_data_valid_random(ano_inicio, ano_fim)
		self.titular = self._get_titular_random()
		super().__init__(self.titular, self.bandeira, self.titular, self.validade,
		                 self.numero)

	@staticmethod
	def _get_numero_str_random():
		return "".join([str(randint(0, 9)) for i in range(16)])

	@staticmethod
	def _get_bandeira_random():
		with open(path_cartao_bandeiras, 'r') as arq:
			bandeiras = [linha.strip() for linha in arq]

		return choice(bandeiras)

	@staticmethod
	def _get_data_valid_random(ano_inicio, ano_fim):
		mes = randint(1, 12)
		ano_fim = randint(ano_inicio, ano_fim)
		str_mes = mes if mes > 9 else '0' + str(mes)
		return "01/%s/%s" % (str_mes, ano_fim)

	@staticmethod
	def _get_tipo_random():
		return choice(["crédito", "débito"])[0]

	@staticmethod
	def _get_titular_random():
		with open(path_fisica_nome, 'r') as arq:
			bandeiras = [linha.strip() for linha in arq]

		return choice(bandeiras)


class ContatoRandom(Contato):

	def __init__(self, nome_completo, tipo=("telefone","celular", "email")):
		self.nome = nome_completo

		# Se o tipo recebido for vazio, sorteie;
		self.tipo = tipo if tipo else self._get_tipo_random()
		self.valor = self._get_valor()

		super().__init__(self.valor, self.tipo)

	def _get_valor(self):

		valor = ""

		# Se for celular ou telefone;
		if (self.tipo.lower() in ["telefone", "celular"]):

			ddd = Util.get_linha_random(path_contato_ddd)

			#Pegue o(s) primeiro(s) dígito(s);
			if (self.tipo.lower() == "telefone"): num_str = "3"

			else: num_str = "9" + choice(['8', '9'])

			#Pegue o restante dos dígitos randomicamente e já converta para str;
			num_str = num_str + ("".join([str(randint(0, 9)) for i in range(7)]))

			#Formate de acordo com padrão: (ddd) xxxx-xxxx;
			valor = "(%s) %s-%s" % (ddd, num_str[0:-4], num_str[-4:])

		elif (self.tipo.lower() == "email"):
			valor = Util.get_email_random(self.nome)

		return valor

	@staticmethod
	def _get_tipo_random():
		return choice(["telefone", "celular", "email"])

class ClienteRandom(_FisicaRandom, Cliente):

	def __init__(self, nome="", sexo=''):
		_FisicaRandom.__init__(self, nome, sexo)

	def print(self):
		super().print()
		print("\nCONTATOS:")

		for i, contato in enumerate(self.contatos):

			jmp = ""

			if (contato.tipo.lower() in ["email", "celular"]):
				jmp = "\t"

			print("%d\t| TIPO -> '%s'\t%sVALOR -> '%s'"
			      % (i + 1, contato.tipo, jmp, contato.valor))


class FuncionarioRandom(_FisicaRandom, Funcionario):

	def __init__(self):
		_FisicaRandom.__init__(self)
		self.set_setor_cargo()

	def set_setor_cargo(self):
		setores = ["ATENDIMENTO", "VENDAS", "ESTOQUE", "LIMPEZA", "TI", "ADMINISTRATIVO"]
		escolha = randint(0, len(setores) - 1)
		cargos = []

		if (escolha == 0):
			cargos = ["operador de caixa", "empacotador", "fiscal de caixa", "segurança",
			          "porteiro", "motoboy"]

		elif (escolha == 1):
			cargos = ["balconista", "açougue", "balconista padaria", "balconista peixaria",
			          "balconista frios e laticínios"]

		elif (escolha == 2):
			cargos = ["repositor de estoque"]

		elif (escolha == 3):
			cargos = ["auxiliar de serviços gerais"]

		elif (escolha == 4):
			cargos = ["técnico em informática", "técnico em suporte e manutenção",
			          "administrador de redes"]

		elif (escolha == 5):
			cargos = ["subgerente", "gerente", "técnico em recursos humanos",
			          "publicitário"]

		self.cargo = choice(cargos)
		self.setor = setores[escolha]


class FornecedorRandom(_JuridicaRandom, Fornecedor):

	def __init__(self):
		self.nome = Util.get_linha_random(path_fornecedor_nome)
		_JuridicaRandom.__init__(self, self.nome)


class SupermercadoRandom(_JuridicaRandom, Supermercado):

	def __init__(self):
		self.nome = Util.get_linha_random(path_supermercado_nome)
		_JuridicaRandom.__init__(self, self.nome)
		self.unidade = self.bairro
		self.latitude = uniform(-45, 45)
		self.longitude = uniform(-45, 45)


class LoteRandom(Lote):

	_ano_compra = 2017

	def __init__(self, unidades=50):
		self.data_compra = Util.get_data_random(2017, 2018, mes_fim=6)
		self.fabricacao = Util.get_data_random(2016, 2017, mes_fim=1)
		self.validade = Util.get_data_random(2018, 2020)
		self.quantidade = unidades
		self.identificador = Util.get_codigo_barras()
		super().__init__(self.data_compra, self.fabricacao, self.validade,
		                 self.quantidade, self.identificador)


class ProdutoRandom(Produto):

	# Porcentagem de custo pra todos produtos;
	porc_custo = 0.25

	# CODIGO PADRÃO UPC

	def __init__(self, numero_lotes=1, unid_por_lote=50,
	             min_prateleira=0, max_prateleira=500):
		nome_preco_marca_categ = Util.get_linha_random(path_produtos).split(";")
		self.nome = nome_preco_marca_categ[0]
		self.preco = float(nome_preco_marca_categ[1])
		self.codigo = Util.get_codigo_barras()
		self.descricao = ""
		self.estoque = 0
		self.custo = self.porc_custo * self.preco
		self.tipo = nome_preco_marca_categ[3]
		self.quant_prateleira = randint(min_prateleira, max_prateleira)
		self.marca = nome_preco_marca_categ[2]
		self.lotes = self._gerar_lotes(numero_lotes, unid_por_lote)
		super().__init__(self.nome, self.preco, self.codigo, self.descricao, self.custo,
		                 self.estoque, self.tipo, self.quant_prateleira, self.marca)

	def _gerar_lotes(self, n=1, unidades_lote=50):
		return [LoteRandom(unidades_lote) for i in range(n)]
