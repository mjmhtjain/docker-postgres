DROP TABLE IF EXISTS todo;

CREATE TABLE cart (id SERIAL PRIMARY KEY,
cartid integer,
itemid integer,
quantity integer,
itemmetadata varchar(256)
done BOOLEAN);