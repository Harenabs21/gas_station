CREATE TABLE IF NOT EXISTS stations (
  id serial PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  place VARCHAR(100) not NULL
);

CREATE TABLE IF NOT EXISTS products(
    id serial PRIMARY KEY,
    product_name VARCHAR(100) not null,
    product_capacity double not null,
    unit_price DOUBLE not null,
    evaporation_rate int not null
);

CREATE TABLE IF NOT EXISTS product_sales(
    id serial PRIMARY KEY,
    sales_price DOUBLE not null,
    sales_capacity double not null,
    id_station int REFERENCES stations(id),
    id_product int REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS supply_station(
    id serial PRIMARY KEY,
    supply_quantity DOUBLE not null,
    supply_date timestamp not null DEFAULT current_timestamp,
    id_station int REFERENCES stations(id),
    id_product int REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS movement_stock(
    id serial PRIMARY KEY,
    movement_type VARCHAR(50) not null,
    movement_datetime timestamp not null default current_timestamp,
    id_station int REFERENCES stations(id),
    id_product int REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS station_and_product(
    id_station int REFERENCES stations(id),
    id_product int REFERENCES products(id)
);