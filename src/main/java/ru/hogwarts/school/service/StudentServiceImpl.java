package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repo.StudentRepo;

import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepo studentRepo;

    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
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

}
