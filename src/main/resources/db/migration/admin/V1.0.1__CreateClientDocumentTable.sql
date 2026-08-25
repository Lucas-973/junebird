CREATE TABLE client_document
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    client_id BIGINT NOT NULL REFERENCES client(id),
    document_type_id BIGINT NOT NULL REFERENCES public.document_type(id),
    document VARCHAR(100) NOT NULL,

    UNIQUE (client_id, document_type_id, document)
);