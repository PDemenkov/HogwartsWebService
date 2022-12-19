package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repo.CountRepo;
import ru.hogwarts.school.repo.StudentRepo;

import java.util.Collection;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepo studentRepo;
    private CountRepo countRepo;

    public StudentServiceImpl(StudentRepo studentRepo, CountRepo countRepo) {
        this.studentRepo = studentRepo;
        this.countRepo = countRepo;
    }

    @Override
    public Student addStudent(Student student) {
        return this.studentRepo.save(student);
    }

    @Override
    public Student findStudent(long id) {
        return this.studentRepo.findById(id).get();
    }

    @Override
    public Student editStudent(long id, Student student) {
        return this.studentRepo.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        this.studentRepo.deleteById(id);
    }

    @Override
    public Collection<Student> findByAge(int age) {
        return studentRepo.findByAge(age);
    }

    @Override
    public Collection<Student> getAllStud() {
        return this.studentRepo.findAll();
    }

    @Override
    public Collection<Student> findAllByAgeBetween(int from, int to) {
        return this.studentRepo.findAllByAgeBetween(from,to);
    }

    public Integer getStudentsCount() {
     return  countRepo.getAllByIdIsNotNull();
    }

    public Double getAverageAge() {
        return countRepo.getAverageAge();
    }

    public List<Student> getStudGreaterIdDesc5() {
        return countRepo.getStudentByAgeIsGreaterThanOrderById();
    }
}
