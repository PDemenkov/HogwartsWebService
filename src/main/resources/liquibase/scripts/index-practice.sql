-- liquibase formatted sql

-- changeset pdemenkov:1
CREATE INDEX student_name_index ON student (name);

-- changeset pdemenkov:2
CREATE INDEX faculty_name_and_color ON faculty (name,color);

-- Добавить два индекса, используя миграции:
-- 1) Индекс для поиска по имени студента.
-- 2) Индекс для поиска по названию и цвету факультета.