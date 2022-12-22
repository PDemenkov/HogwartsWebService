-- Составить первый JOIN-запрос, чтобы получить информацию обо всех студентах
-- (достаточно получить только имя и возраст студента) школы Хогвартс вместе с названиями факультетов.
select student.name, student.age, f.name
from student
         INNER JOIN faculty f on student.faculty_id = f.id order by f.name;
-- Составить второй JOIN-запрос, чтобы получить только тех студентов, у которых есть аватарки.
select student.name, student.age
from student
         INNER JOIN avatar a on student.id = a.student_id ;
-- Составить второй JOIN-запрос, чтобы получить только тех студентов, у которых есть аватарки.v2
select name, age
from student s
         NATURAL JOIN avatar ;







