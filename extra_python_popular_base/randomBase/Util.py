from random import choice, randint
from datetime import datetime
import unidecode

class Util():

	@staticmethod
	def get_linha_random(nome_arq):
		with open(nome_arq, 'r') as arq:
			linhas = [linha.strip() for linha in arq]

		return choice(linhas)

	@staticmethod
	def remover_acentos(string):
		return unidecode.unidecode(string)

	@staticmethod
	def get_data_random(ano_inicio=1950, ano_fim=datetime.now().year - 16,
						mes_ini=1, mes_fim=12):
		ano_final = randint(ano_inicio, ano_fim)
		mes = randint(1, 12) if ano_final < ano_fim else randint(1, mes_fim)
		dia = randint(1, 28) if mes == 2 else randint(1, 30)
		str_dia = dia if dia > 9 else '0' + str(dia)
		str_mes = mes if mes > 9 else '0' + str(mes)

		return "%s/%s/%d" % (str_dia, str_mes, ano_final)
	@staticmethod
	def get_email_random(nome):
		nomes = Util.remover_acentos(nome).strip().lower().split(" ")
		num_rand = randint(1, 666)
		separador = choice(['.', '-', '_', '']) if len(nomes) > 1 else ""
		iniciais = "".join([nomes[i][0] for i in range(1, len(nomes))])
		email = ["gmail.com", "yahoo.com", "live.com", "outlook.com",
		         "bol.com.br", "r7.com", "live.com.br", "hotmail.com",
		         "fastmail.com", "icloud.com", "hushmail.com", "protonmail.com"]

		server = choice(email)
		return "%s%s%s%d@%s" % (nomes[0], separador, iniciais, num_rand, server)

	@staticmethod
	def get_codigo_barras():
		codigo = str(789)
		tam_id_org = 6
		digitos2 = [str(randint(0, 9)) for i in range(tam_id_org + 3)]
		codigo += "".join(digitos2)

		soma = 0
		dig_verif = 0
		pesos = [1, 3]
		i = 0

		for numero in codigo:
			soma += int(numero) * pesos[i]

			#i recebe 1 se ele for 0, senão, ele recebe 1;
			i = 1 if i == 0 else 1

		while (soma + dig_verif) % 10 != 0:
			dig_verif += 1

		codigo += str(dig_verif)

		return codigo