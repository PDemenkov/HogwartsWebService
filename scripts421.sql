-- С прошлых уроков у нас есть две таблицы: Student и Faculty. Необходимо для них создать следующие ограничения:
--
-- - Возраст студента не может быть меньше 16 лет.
-- - Имена студентов должны быть уникальными и не равны нулю.
-- - Пара “значение названия” - “цвет факультета” должна быть уникальной.
-- - При создании студента без возраста ему автоматически должно присваиваться 20 лет.

create table student
(
    id   integer primary key ,
    name text UNIQUE not null ,
    age  integer check ( age >= 16 ) default 20
);

create table faculty(
    id integer primary key,
    name text UNIQUE not null,
    color text UNIQUE not null
)
