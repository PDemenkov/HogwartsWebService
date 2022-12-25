package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Student addStudent(Student student) {
        logger.info("Was invoked method create student");
        return this.studentRepo.save(student);
    }

    @Override
    public Student findStudent(long id) {
        logger.info("Was invoked method find student by id {}",id);
        Student findStud = this.studentRepo.findById(id).get();
        logger.info("Student with id {} is {}",id,findStud);
        return findStud;
    }

    @Override
    public Student editStudent(long id, Student student) {
        logger.info("Was invoked method edit student by id {}",id);
        Student newStud = this.studentRepo.save(student);
        logger.info("Student with id {} was edit",id);
        return newStud;
    }

    @Override
    public void deleteStudent(long id) {
        logger.info("Was invoked method delete student by id");
        this.studentRepo.deleteById(id);
        logger.info("student with id {} was deleted",id);
    }

    @Override
    public Collection<Student> findByAge(int age) {
        logger.info("Was invoked method find student by age");
        Collection<Student> byAge = studentRepo.findByAge(age);
        logger.info("Student by age {} is {}",age,byAge);
        return byAge;
    }

    @Override
    public Collection<Student> getAllStud() {
        logger.info("Was invoked method for all students");
        Collection<Student> all = this.studentRepo.findAll();
        logger.info("All students: {}",all);
        return all;
    }

    @Override
    public Collection<Student> findAllByAgeBetween(int from, int to) {
        logger.info("Was invoked method to find all students between ages");
        Collection<Student> findAll = this.studentRepo.findAllByAgeBetween(from, to);
        logger.info("Students between age {} and {} are {}",from,to,findAll);
        return findAll;
    }

    public Integer getStudentsCount() {
        logger.info("Was invoked method to get Number of students");
        Integer count = countRepo.getAllByIdIsNotNull();
        logger.info("There are {} students",count);
        return count;
    }

    public Double getAverageAge() {
        logger.info("Was invoked method of average Age");
        Double avg = countRepo.getAverageAge();
        logger.info("Average age of students is {}",avg);
        return avg;
    }

    public List<Student> getStudGreaterIdDesc5() {
        logger.info("Was invoked method of 5 last stud by id");
        List<Student> studentList = countRepo.getStudentByAgeIsGreaterThanOrderById();
        logger.info("Last 5 students by id : {} ",studentList);
        return studentList;
    }
}
