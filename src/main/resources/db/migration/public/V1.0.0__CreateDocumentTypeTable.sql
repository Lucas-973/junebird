CREATE TABLE document_type
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    code VARCHAR(50) NOT NULL,
    description VARCHAR(200) NOT NULL,
    country_code CHAR(2) NOT NULL,

    UNIQUE (country_code, code)
);