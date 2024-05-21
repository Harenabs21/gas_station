insert into stations (name,place) values ('total','Antananarivo');

insert into products (product_name,unit_price) values('diesel fuel',4900.0),('gasoline',5900.0),('oil',2130.0);

insert into evaporation_rate (rate_value,id_station,id_product) values(50,1,1),(100,1,2),(20,1,3);

insert into movement_stock (movement_type, movement_quantity,movement_amount, id_station, id_product) values ('ENTRY',600.0,0.0,1,1),('ENTRY',600.0,0.0,1,2),('ENTRY',600.0,0.0,1,3);

insert into stock (product_capacity, id_station, id_product) values (600.0,1,1),(600.0,1,2),(600.0,1,3);