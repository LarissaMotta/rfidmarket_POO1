CREATE TABLE public.pessoa (
nome character varying(75),
id serial,
numero integer,
rua character varying(80),
cep character(9),
bairro character varying(50),
estado character(2),
cidade character varying(50),
CONSTRAINT id_pessoa PRIMARY KEY (id)
);

CREATE TABLE public.cartao (
id serial,
nome_titular character varying(75),
validade date,
bandeira character varying(35),
numero character(16),
tipo character(1),
CONSTRAINT cartao_pkey PRIMARY KEY (id),
CONSTRAINT cartao_numero_key UNIQUE (numero)
);

CREATE TABLE public.cliente
(
fk_pessoa integer NOT NULL,
CONSTRAINT cliente_pkey PRIMARY KEY (fk_pessoa)
);

CREATE TABLE public.contato
(
id serial,
descricao character varying(150),
tipo character varying(20),
fk_pessoa integer,
CONSTRAINT contato_pkey PRIMARY KEY (id),
CONSTRAINT fk_pessoa FOREIGN KEY (fk_pessoa) REFERENCES public.pessoa (id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT,
CONSTRAINT descricao_tipo UNIQUE (descricao, tipo)
);

CREATE TABLE public.fisica
(
data_nasc date,
cpf character(14),
senha character varying(255),
fk_pessoa integer NOT NULL,
genero character(1),
login character varying(150),
CONSTRAINT id_pessoa_fisica PRIMARY KEY (fk_pessoa),
CONSTRAINT fk_pessoa FOREIGN KEY (fk_pessoa)
REFERENCES public.pessoa (id) MATCH SIMPLE
ON UPDATE RESTRICT ON DELETE RESTRICT,
CONSTRAINT fisica_login_key UNIQUE (login),
CONSTRAINT login UNIQUE (cpf)
);

CREATE TABLE public.fornecedor
(
fk_pessoa serial,
CONSTRAINT fornecedor_pkey PRIMARY KEY (fk_pessoa)
);

CREATE TABLE public.funcionario
(
fk_pessoa serial,
cargo character varying(50),
setor character varying(50),
CONSTRAINT funcionario_pkey PRIMARY KEY (fk_pessoa)
);

CREATE TABLE public.juridica
(
cnpj character(18),
fk_pessoa integer NOT NULL,
CONSTRAINT id_pessoa_juridica PRIMARY KEY (fk_pessoa),
CONSTRAINT fk_pessoa FOREIGN KEY (fk_pessoa)
REFERENCES public.pessoa (id) MATCH SIMPLE
ON UPDATE RESTRICT ON DELETE RESTRICT,
CONSTRAINT cnpj UNIQUE (cnpj)
);

CREATE TABLE public.lote
(
id serial,
data_compra date,
fabricacao date,
validade date,
quantidade integer,
identificador character(30),
fk_produto integer,
CONSTRAINT lote_pkey PRIMARY KEY (id),
CONSTRAINT lote_identificador_fk_produto_key UNIQUE (identificador, fk_produto)
);

CREATE TABLE public.produto
(
id serial,
nome character varying(75) unique,
preco double precision,
codigo character varying(85),
descricao text,
custo double precision,
estoque integer,
tipo character varying(50),
quant_prateleira integer,
marca character varying(50),
CONSTRAINT produto_pkey PRIMARY KEY (id)
);

CREATE TABLE public.supermercado
(
fk_pessoa serial,
longitude double precision,
latitude double precision,
unidade character varying(50),
CONSTRAINT supermercado_pkey PRIMARY KEY (fk_pessoa)
);