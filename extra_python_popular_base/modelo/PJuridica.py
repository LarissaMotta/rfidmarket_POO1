from extra_python_popular_base.modelo.Pessoa import Pessoa


class PJuridica(Pessoa):
    cnpj = str
    id = int

    def __init__(self, cnpj, nome, estado, cidade, bairro, rua, numero, cep):
        super().__init__(nome, estado, cidade, bairro, rua, numero, cep)
        self.cnpj = cnpj

    def print(self):
        super().print()
        print("CNPJ\t-> %s" % self.cnpj)