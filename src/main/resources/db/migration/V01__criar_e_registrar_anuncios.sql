CREATE TABLE anuncio (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	cliente VARCHAR(50) NOT NULL,
	data_inicio DATE NOT NULL,
	data_termino DATE NOT NULL,
	investimento_dia FLOAT NOT null
);

INSERT INTO anuncio (nome, cliente, data_inicio, data_termino, investimento_dia)
VALUES ('DevSuperior', 'Nelio Alves', '2021-05-12', '2021-12-12', 50);

INSERT INTO anuncio (nome, cliente, data_inicio, data_termino, investimento_dia)
VALUES ('Curso em Video', 'Gustavo Guanabara', '2020-01-12', '2020-12-12', 20);

INSERT INTO anuncio (nome, cliente, data_inicio, data_termino, investimento_dia)
VALUES ('DevDojo', 'Willian Suane', '2020-06-12', '2021-06-12', 60);

INSERT INTO anuncio (nome, cliente, data_inicio, data_termino, investimento_dia)
VALUES ('Estudonauta', 'Gustavo Guanabara', '2021-05-12', '2021-12-12', 105);