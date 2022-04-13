CREATE TABLE actors (
                        actorid bigserial unique,
                        actorname varchar(255) null,
                        description text,
                        photo varchar null,
                        PRIMARY KEY (actorid)
)