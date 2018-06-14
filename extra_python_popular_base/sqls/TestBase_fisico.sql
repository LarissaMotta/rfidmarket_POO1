--Entidades primárias
CREATE TABLE PESSOA (id SERIAL PRIMARY KEY);

CREATE TABLE CARTAO (

	id SERIAL PRIMARY KEY,
	nome_titular VARCHAR(75),
	validade DATE,
	bandeira VARCHAR(35),
	numero CHAR(16) UNIQUE,
	tipo CHAR
);

CREATE TABLE CONTATO (

	id SERIAL PRIMARY KEY,
	descricao VARCHAR(150),
	tipo VARCHAR(20),
	fk_pessoa INTEGER REFERENCES PESSOA (id),
	CONSTRAINT descricao_tipo UNIQUE (descricao, tipo)
);

CREATE TABLE CLIENTE (

	fk_pessoa INTEGER PRIMARY KEY,
	
	cpf CHAR(14) UNIQUE,
	data_nasc DATE,
	genero CHAR,
	login VARCHAR(150) UNIQUE,
	senha VARCHAR(255),
	
	nome VARCHAR(75),
	
	numero INTEGER,
	rua VARCHAR(80),
	cep CHAR(9),
	bairro VARCHAR(50),
	estado CHAR(2),
	cidade VARCHAR(50)
);

ALTER TABLE CLIENTE ADD CONSTRAINT fk_fisica FOREIGN KEY (fk_pessoa)
REFERENCES PESSOA (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE FUNCIONARIO (

	fk_pessoa SERIAL PRIMARY KEY,
	cargo VARCHAR(50),
	setor VARCHAR(50),
	
	nome VARCHAR(75),
	numero INTEGER,
	rua VARCHAR(80),
	cep CHAR(9),
	bairro VARCHAR(50),
	estado CHAR(2),
	cidade VARCHAR(50),

	login VARCHAR(150) UNIQUE,
	cpf CHAR(14) UNIQUE,
	data_nasc DATE,
	senha VARCHAR(255),
	genero CHAR
);

ALTER TABLE FUNCIONARIO ADD CONSTRAINT fk_fisica FOREIGN KEY (fk_pessoa)
REFERENCES PESSOA (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE FORNECEDOR (

	fk_pessoa SERIAL PRIMARY KEY,
	cnpj CHAR(18),
	
	nome VARCHAR(75),
	numero INTEGER,
	rua VARCHAR(80),
	cep CHAR(9),
	bairro VARCHAR(50),
	estado CHAR(2),
	cidade VARCHAR(50)
	);

ALTER TABLE FORNECEDOR ADD CONSTRAINT fk_juridica FOREIGN KEY (fk_pessoa)
REFERENCES PESSOA (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE SUPERMERCADO (

	fk_pessoa SERIAL PRIMARY KEY,
	longitude FLOAT8,
	latitude FLOAT8,
	unidade VARCHAR(50),
	cnpj CHAR(18),
	
	nome VARCHAR(75),
	numero INTEGER,
	rua VARCHAR(80),
	cep CHAR(9),
	bairro VARCHAR(50),
	estado CHAR(2),
	cidade VARCHAR(50)
);

ALTER TABLE SUPERMERCADO ADD CONSTRAINT fk_juridica FOREIGN KEY (fk_pessoa)
REFERENCES PESSOA (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE PRODUTO (

	id SERIAL PRIMARY KEY,
	nome VARCHAR(75),
	preco FLOAT8,
	codigo VARCHAR(85),
	descricao TEXT,
	custo FLOAT8,
	estoque INTEGER,
	tipo VARCHAR(50),
	quant_prateleira INTEGER,
	marca VARCHAR(50)
);


CREATE TABLE LOTE (
	id SERIAL PRIMARY KEY,
	data_compra DATE,
	fabricacao DATE,
	validade DATE,
	quantidade INTEGER,
	identificador CHAR(30),
	fk_produto INTEGER,
	UNIQUE (identificador, fk_produto)
);
