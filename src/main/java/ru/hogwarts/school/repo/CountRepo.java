package ru.hogwarts.school.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.List;

@Repository
public interface CountRepo extends JpaRepository<Student, Long> {

    @Query(nativeQuery = true, value = "select count(*) from student")
    Integer getAllByIdIsNotNull();

    @Query(nativeQuery = true, value = "select avg(age) from student")
    Double getAverageAge();

    @Query(nativeQuery = true, value = "SELECT * from student order by id DESC LIMIT 5")
    List<Student> getStudentByAgeIsGreaterThanOrderById();
}
