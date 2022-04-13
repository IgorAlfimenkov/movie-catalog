CREATE TABLE customer_film (
                               id bigserial not null,
                               customer_id integer NOT NULL,
                               film_id integer NOT NULL,
                               primary key (id),
                               foreign key (customer_id) references customers(id),
                               foreign key (film_id) references film(id)
);