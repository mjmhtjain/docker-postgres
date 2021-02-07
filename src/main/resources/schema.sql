DROP TABLE IF EXISTS cart;

CREATE TABLE cart (id SERIAL PRIMARY KEY,
cartid integer,
itemid integer,
quantity integer,
itemmetadata varchar(256)
);