from extra_python_popular_base.modelo.PJuridica import PJuridica


class Fornecedor(PJuridica):

    id = int

    def __init__(self, cnpj, nome, estado, cidade, bairro, rua, numero, cep):
        super().__init__(cnpj, nome, estado, cidade, bairro, rua, numero, cep)