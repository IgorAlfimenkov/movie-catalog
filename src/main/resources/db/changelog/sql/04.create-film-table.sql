CREATE TABLE film (
                        id bigserial unique,
                        filmname varchar(500) null,
                        description text,
                        poster varchar null,
                        trailer varchar null,
                        year int,
                        duration int,
                        companyname varchar(255),
                        PRIMARY KEY (id)
)