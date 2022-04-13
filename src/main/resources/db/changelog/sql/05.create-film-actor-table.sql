CREATE TABLE film_actor (
                                id bigserial not null,
                                film_id integer NOT NULL,
                                actor_id integer NOT NULL,
                                primary key (id),
                                foreign key (film_id) references film(id),
                                foreign key (actor_id) references actors(actorid)
);