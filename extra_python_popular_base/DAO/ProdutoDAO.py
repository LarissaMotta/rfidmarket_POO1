import psycopg2

from extra_python_popular_base.DAO.DBCore import DBCore
from extra_python_popular_base.DAO.LoteDAO import LoteDAO
from extra_python_popular_base.modelo.Produto import Produto


class ProdutoDAO():

	@staticmethod
	def insert(data_base, prod=Produto):

		def get_insert_sql(p):
			cols = ["nome", "preco", "codigo", "descricao", "custo", "estoque",
			        "tipo", "quant_prateleira", "marca"]
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
