CREATE TABLE IF NOT EXIST usuario_model (
id SERIAL PRIMARY KEY,
codigo VARCHAR(255) UNIQUE NOT NULL,
nome VARCHAR(255) NOT NULL,
data_de_nascimento DATE,
foto BYTEA,
data_inclusao TIMESTAMP WITH TIME ZONE NOT NULL,
ultima_modificacao TIMESTAMP WITH TIME ZONE NOT NULL
);