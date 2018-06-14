from extra_python_popular_base.DAO.CartaoDAO import CartaoDAO
from extra_python_popular_base.DAO.ClienteDAO import ClienteDAO
from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.DAO.FornecedorDAO import FornecedorDAO
from extra_python_popular_base.DAO.FuncionarioDAO import FuncionarioDAO
from extra_python_popular_base.DAO.ProdutoDAO import ProdutoDAO
from extra_python_popular_base.DAO.Supermercado import SupermercadoDAO
from extra_python_popular_base.randomBase.ObjRandom import *


def drop_all_tables(database):
	database.drop_all()

def create_all_tables(sql_create, database):
	database.create(sql_create[1:])

def load_sql(file_path):
	sql = ''

	with open(file_path, 'r') as arq:
		for linha in arq:
			sql += linha.strip() + " "

	return sql

def main():

	print(path_corrente)
	# Arquivo sql de criação do modelo físico;
	sql_create_path = path.join(path_corrente, "sqls/modelo_fisico.sql")
	base_dados = DBCore(database_nome="x", usuario="postgres", senha="antonio")

	# Ative o banco, remova todas tabelas e recrie a base usando o arquivo acima;
	base_dados.connect()
	base_dados.drop_all()
	create_all_tables(load_sql(sql_create_path), base_dados)

	# Máximo de cartões é número de nomes das pessoas * 10^16
	def add_cartao(quantidade):
		cartoes = [CartaoRandom(2018, 2025) for i in range(quantidade)]
		CartaoDAO.insert_n(base_dados, cartoes)

	# Máximo de 610 sem repetição;
	def add_cliente(quantidade, inic=0, fim=0):

		with open(path_pessoa_nome_sexo_csv, "r") as arq:
			clientes = []

			intervalo = True if (inic >= 0 and fim > 0) else False

			for i, linha in enumerate(arq):

				if (intervalo and i >= inic and i < fim):
					nome_sexo = linha.strip().split(";")
					cliente = ClienteRandom(nome_sexo[0], nome_sexo[1])
					clientes.append(cliente)

				elif (not intervalo and i + 1 >= quantidade): break

			ClienteDAO.insert_n(base_dados, clientes)

	# Máximo de 240 sem repetição;
	def add_fornecedor(quantidade):

		with open(path_fornecedor_nome, "r") as arq:
			fornecedores = []

			for i, linha in enumerate(arq):
				fornecedores.append(FornecedorRandom(linha.strip()))

				if (i + 1 >= quantidade): break

			FornecedorDAO.insert_n(base_dados, fornecedores)

	# Máximo de 610 sem repetição;
	def add_funcionario(quantidade, inic=0, fim=0):

		with open(path_pessoa_nome_sexo_csv, "r") as arq:
			funcionarios = []

			intervalo = True if (inic >= 0 and fim > 0) else False

			for i, linha in enumerate(arq):

				if (intervalo and i >= inic and i < fim):
					nome_sexo = linha.strip().split(";")
					funcionario = FuncionarioRandom(nome_sexo[0], nome_sexo[1])
					funcionarios.append(funcionario)

				elif (not intervalo and i + 1 >= quantidade): break

			FuncionarioDAO.insert_n(base_dados, funcionarios)

	# Máximo de 731 sem repetição;
	def add_produto(quantidade, min_lotes=1, max_lotes=10, min_prat=0, max_prat=10):

		qtd_unidades_lote = [25, 50, 75, 100, 125, 150, 175, 200]

		with open(path_produtos, "r") as arq:
			produtos = []

			for i, linha in enumerate(arq):
				nome_preco_marca_categ = linha.strip().split(";")
				n_lotes = randint(min_lotes, max_lotes)
				unid_lote = choice(qtd_unidades_lote)
				nome = nome_preco_marca_categ[0]
				preco = float(nome_preco_marca_categ[1])
				marca = nome_preco_marca_categ[2]
				categ = nome_preco_marca_categ[3]
				prod = ProdutoRandom(nome=nome, marca=marca, categ=categ, preco=preco,
				                     numero_lotes=n_lotes, unid_por_lote=unid_lote,
				                     min_prateleira=min_prat, max_prateleira=max_prat)
				produtos.append(prod)

				if (i + 1 >= quantidade): break

			ProdutoDAO.insert_n(base_dados, produtos)

	# Máximo de 40 sem repetição;
	def add_supermercado(quantidade):

		with open(path_supermercado_nome, "r") as arq:
			supermercados = []

			for i, linha in enumerate(arq):
				supermercados.append(SupermercadoRandom(linha.strip()))

				if i + 1 >= quantidade: break

			SupermercadoDAO.insert_n(base_dados, supermercados)

	# Adicione os objetos a base de dados
	add_cliente(n_clientes, inic=0, fim=n_clientes)
	add_cartao(n_cartoes)
	add_funcionario(n_funcionarios, inic=n_clientes, fim=600)
	add_supermercado(n_supermercados)
	add_fornecedor(n_fornecedores)
	add_produto(n_produtos, n_lotes_min, n_lotes_max, min_prat=0, max_prat=100)

	# feche a base de dados;
	base_dados.close()

	return 0


# Número de cada objeto que será gerado na base de dados;
n_cartoes = 1000
n_clientes = 500
n_funcionarios = 100
n_supermercados = 50
n_fornecedores = 200
n_produtos = 725
n_lotes_min = 1
n_lotes_max = 20

# Caminho base dos arquivos a serem usados
path_corrente = path.join(path.dirname(path.abspath(__file__)))
path_arquivos = path.join(path_corrente, "randomBase")

# Caminho dos arquivos de dados usados para criar os objetos randômicos;
path_pessoa_nome_sexo_csv = path.join(path_arquivos, "nome_sexo.csv")
path_fornecedor_nome = path.join(path_arquivos, "fornecedores_nomes.txt")
path_produtos = path.join(path_arquivos, "produtos.txt")
path_supermercado_nome = path.join(path_arquivos, "supermercado_nomes.txt")

main()
