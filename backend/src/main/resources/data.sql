-- CEPS randômicos, vindos do site: https://www.geradordecep.com.br/

INSERT INTO ENDERECO (cep, cidade, logradouro, numero, tipo) VALUES
('84070240', 'Ponta Grossa', 'Rua Francisco Camerino', '221', 1),
('08250610', 'São Paulo', 'Rua Gilberto Randon', '431A', 2),
('13185177', 'Hortolândia', 'Rua João Manoel de Moraes', '1785B', 2),
('69307090', 'Boavista', 'Rua Aroeira', '12', 2),
('49090800', 'Aracaju', 'Rua José Olímpio dos Santos', '55', 2),
('89036501', 'Blumenau', 'Rua Gustavo Budag', '177', 1),
('57608070', 'Palmeira dos Índios', 'Rua Antônio Pau Ferro', '889', 2),
('54440580', 'Jaboatão dos Guararapes', 'Rua Cambuci', '998', 2),
('58066234', 'João Pessoa', 'Rua Wirley Lins Ribeiro', '123', 2),
('60743350', 'Fortaleza', 'Rua Bom Pastor', '8', 2);

INSERT INTO PESSOA (data_nascimento, nome) VALUES
('1999-02-09', 'Igor Martins'),
('1998-12-02', 'Letícia Marques');

UPDATE ENDERECO SET pessoa_id = 1 WHERE id = 1;
UPDATE ENDERECO SET pessoa_id = 1 WHERE id = 2;
UPDATE ENDERECO SET pessoa_id = 1 WHERE id = 3;
UPDATE ENDERECO SET pessoa_id = 1 WHERE id = 4;
UPDATE ENDERECO SET pessoa_id = 1 WHERE id = 5;
UPDATE ENDERECO SET pessoa_id = 2 WHERE id = 6;
UPDATE ENDERECO SET pessoa_id = 2 WHERE id = 7;
UPDATE ENDERECO SET pessoa_id = 2 WHERE id = 8;
UPDATE ENDERECO SET pessoa_id = 2 WHERE id = 9;
UPDATE ENDERECO SET pessoa_id = 2 WHERE id = 10;
