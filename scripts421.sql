-- - Возраст студента не может быть меньше 16 лет.
ALTER TABLE student
    add CONSTRAINT age_check check ( age >= 16 );
-- - Имена студентов должны быть уникальными и не равны нулю.
ALTER TABLE student
    add CONSTRAINT name_unique_notNull UNIQUE (name);
ALTER TABLE student
    alter column name set not null;
--Пара “значение названия” - “цвет факультета” должна быть уникальной.
ALTER TABLE faculty
    add CONSTRAINT name_color_unique UNIQUE (name, color);
-- - При создании студента без возраста ему автоматически должно присваиваться 20 лет
ALTER TABLE student
    alter column age set default 20;






