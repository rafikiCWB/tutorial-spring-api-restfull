CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE funcionario (
                       id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                       nome VARCHAR(100) NOT NULL,
                       papel VARCHAR(250) NOT NULL
);