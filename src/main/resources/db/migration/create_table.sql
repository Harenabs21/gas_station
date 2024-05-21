CREATE TABLE IF NOT EXISTS stations (
  id serial PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  place VARCHAR(100) not NULL
);

CREATE TABLE IF NOT EXISTS products(
    id serial PRIMARY KEY,
    product_name VARCHAR(100) not null,
    unit_price DOUBLE PRECISION not null
); 

CREATE TABLE IF NOT EXISTS evaporation_rate(
    id serial primary key ,
    rate_value int not null,
    id_station int references stations(id),
    id_product int references products(id)
);

CREATE TABLE IF NOT EXISTS movement_stock(
    id serial PRIMARY KEY,
    movement_type VARCHAR(50) not null,
    movement_quantity DOUBLE PRECISION  not null,
    movement_amount DOUBLE PRECISION not null,
    movement_datetime timestamp not null default current_timestamp,
    id_station int REFERENCES stations(id),
    id_product int REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS stock(
    id serial PRIMARY KEY,
    product_capacity DOUBLE PRECISION  not null,
    stock_datetime timestamp not null default current_timestamp,
    id_station int REFERENCES stations(id),
    id_product int REFERENCES products(id)
);