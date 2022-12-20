--     В этом задании по описанию необходимо спроектировать таблицы,
--     связи между ними и корректно определить типы данных.

--     Здесь не важно, какой тип вы выберете, например, для данных,
--     представленных в виде строки (varchar или text).

--     Важно, что вы выберете один из строковых типов, а не числовых, например.
--
-- Описание структуры: у каждого человека есть машина.
-- Причем несколько человек могут пользоваться одной машиной.

-- У каждого человека есть имя, возраст и признак того, что у него есть права (или их нет).
-- У каждой машины есть марка, модель и стоимость.

-- Также не забудьте добавить таблицам первичные ключи и связать их.

create table person(
    id SERIAL PRIMARY KEY ,
    name text not null ,
    age integer not null check ( age>0) ,
    driverLicense BOOLEAN,
    car_id integer references car(id)
);

INSERT INTO person ( name, age, driverLicense,car_id) VALUES ('name1', '18',TRUE,3);
INSERT INTO person ( name, age, driverLicense,car_id) VALUES ('name2', '22',TRUE,2);
INSERT INTO person ( name, age, driverLicense,car_id) VALUES ('name3', '14',FALSE,1);
INSERT INTO person ( name, age, driverLicense,car_id) VALUES ('name4', '25',TRUE,3);
INSERT INTO person ( name, age, driverLicense,car_id) VALUES ('name5', '26',TRUE,2);

create table car(
    id SERIAL PRIMARY KEY ,
    model text not null ,
    brand text not null ,
    price integer not null
);

INSERT INTO car ( model, brand, price) VALUES ( 'kia','sorento',1000000);
INSERT INTO car ( model, brand, price) VALUES ( 'vaz','2101',1200000);
INSERT INTO car ( model, brand, price) VALUES ( 'bmw','m3',3000000);

-----проверка
select name,age,brand
       from person
NATURAL JOIN car c ;

select name,price,brand
from person
INNER JOIN car c on person.car_id = c.id





