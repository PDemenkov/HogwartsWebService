package ru.hogwarts.school.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepo extends JpaRepository<Student,Long> {
    Collection<Student> findByAge(int age);
}
