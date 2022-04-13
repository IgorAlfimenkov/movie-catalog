CREATE TABLE film_category (
                                id bigserial not null,
                                film_id integer NOT NULL,
                                category_id integer NOT NULL,
                                primary key (id),
                                foreign key (film_id) references film(id),
                                foreign key (category_id) references category(id)
);