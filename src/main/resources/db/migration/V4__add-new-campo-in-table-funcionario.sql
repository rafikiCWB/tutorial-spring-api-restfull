CREATE TABLE IF NOT EXISTS funcionario
(
    id    uuid DEFAULT gen_random_uuid() NOT NULL PRIMARY KEY,
    nome  varchar(255) NOT NULL,
    sobrenome varchar(255) NOT NULL,
    papel varchar(255) NOT NULL
    );