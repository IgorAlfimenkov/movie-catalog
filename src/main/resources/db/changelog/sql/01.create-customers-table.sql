CREATE TABLE customers (
                       id bigserial unique,
                       nickname varchar(15) null,
                       email varchar null,
                       name varchar null,
                       surname varchar null,
                       PRIMARY KEY (id)
);