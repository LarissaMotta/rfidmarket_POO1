import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.DAO.LoteDAO import LoteDAO
from extra_python_popular_base.modelo.Produto import Produto


class ProdutoDAO():

	__cols = ["nome", "preco", "codigo", "descricao", "custo", "estoque", "tipo",
	          "quant_prateleira", "marca"]
	__nome_tabela = "produto"

	# Obtém sql única com todos valores formatados;
	@staticmethod
	def get_insert_all_sql(produtos=[Produto]):

		cols = ProdutoDAO.get_nome_colunas()
		nome_tab = ProdutoDAO.get_nome_tabela()
		sql = "INSERT INTO %s (%s) VALUES" % (nome_tab, (', '.join(cols)))
		sqls_value = []

		i = 0

		# Para cada pessoa, obtenha suas tuplas de valores já formatada;
		for p in produtos:
			vals = [p.nome, p.preco, p.codigo, p.descricao, p.custo, p.estoque,
			        p.tipo, p.quant_prateleira, p.marca]
			i+=1

			try:
				sqls_value.append(DBCore.format_sql_value(vals))

			except ValueError:
				print(vals)
				print("Nº %d ERRRO" %i)
				exit(1)

		sql = sql + '\n' + (',\n'.join(sqls_value)) + ';'
		return sql

	@staticmethod
	def insert_n(data_base, produtos=[Produto]):

		try:
			sql_n_prods = ProdutoDAO.get_insert_all_sql(produtos)
			nome_tab = ProdutoDAO.get_nome_tabela()
			prods_ids = data_base.insert_all(sql_n_prods, nome_tab, "id", 0)

			for i in range(len(prods_ids)):
				produtos[i].id = prods_ids[i]

			print('\n', sql_n_prods)
			lotes = []

			for p in produtos:

				for lote in p.lotes:
					lote.fk_produto = p.id
					lotes.append(lote)

			LoteDAO.insert_n(data_base, lotes)

			return prods_ids

		except psycopg2.ProgrammingError:
			raise

	@staticmethod
	def insert(data_base, prod=Produto):

		def get_insert_sql(p):
			cols = ProdutoDAO.get_nome_colunas()
			vals = [p.nome, p.preco, p.codigo, p.descricao, p.custo, p.estoque,
			        p.tipo, p.quant_prateleira, p.marca]
			return DBCore.format_sql_insert("produto", cols, vals, "id")

		try:
			prod.id = data_base.insert(get_insert_sql(prod))

			for lote in prod.lotes:
				lote.id = LoteDAO.insert(data_base, prod.id, lote)

			return prod.id

		except psycopg2.ProgrammingError:
			raise psycopg2.ProgrammingError("Falha ao inserir produto na base")

	@staticmethod
	def remove(data_base, id_prod):

		def get_remove_sql(p_id):
			return "DELETE FROM produto WHERE id = %d;" %p_id

		data_base.execute(get_remove_sql(id_prod))
		return id_prod

	@staticmethod
	def get_nome_tabela():
		return ProdutoDAO.__nome_tabela

	@staticmethod
	def get_nome_colunas():
		return ProdutoDAO.__cols