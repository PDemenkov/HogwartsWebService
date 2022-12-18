package ru.hogwarts.school.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyRepo extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findByColor(String color);

    Collection<Faculty> findFacultyByNameIgnoreCaseOrColorIgnoreCase(String name, String color);

}
