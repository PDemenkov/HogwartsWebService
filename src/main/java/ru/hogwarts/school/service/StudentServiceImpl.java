package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repo.CountRepo;
import ru.hogwarts.school.repo.StudentRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepo studentRepo;
    private CountRepo countRepo;


    public StudentServiceImpl(StudentRepo studentRepo, CountRepo countRepo) {
        this.studentRepo = studentRepo;
        this.countRepo = countRepo;
    }

    public Object lock = new Object();
    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Student addStudent(Student student) {
        logger.info("Was invoked method create student");
        return this.studentRepo.save(student);
    }

    @Override
    public Student findStudent(long id) {
        logger.info("Was invoked method find student by id {}", id);
        Student findStud = this.studentRepo.findById(id).orElseThrow(StudentNotFoundException::new);
        logger.info("Student with id {} is {}", id, findStud);
        return findStud;
    }

    @Override
    public Student editStudent(long id, Student student) {
        logger.info("Was invoked method edit student by id {}", id);
//        if (!this.studentRepo.findAll().contains(id)) {
//            logger.error("Student {} not found and cant be edit",id);
//            throw new StudentNotFoundException();
//        }
        Student newStud = this.studentRepo.save(student);
        logger.info("Student with id {} was edit", id);
        return newStud;
    }

    @Override
    public void deleteStudent(long id) {
        logger.info("Was invoked method delete student by id");
        if (!this.studentRepo.findAll().contains(id)) {
            logger.error("Student with id {} not found", id);
            throw new StudentNotFoundException();
        }
        this.studentRepo.deleteById(id);
        logger.info("student with id {} was deleted", id);
    }

    @Override
    public Collection<Student> findByAge(int age) {
        logger.info("Was invoked method find student by age");
        Collection<Student> byAge = studentRepo.findByAge(age);
        if (byAge.isEmpty()) {
            logger.error("Student with age {} not found", age);
            throw new StudentNotFoundException();
        }
        logger.info("Student by age {} is {}", age, byAge);
        return byAge;
    }

    @Override
    public Collection<Student> getAllStud() {
        logger.info("Was invoked method for all students");
        Collection<Student> all = this.studentRepo.findAll();
        if (all.isEmpty()) {
            logger.error("There are no any students");
            throw new StudentNotFoundException();
        }
        logger.info("All students: {}", all);
        return all;
    }

    @Override
    public Collection<Student> findAllByAgeBetween(int from, int to) {
        logger.info("Was invoked method to find all students between ages");
        Collection<Student> findAll = this.studentRepo.findAllByAgeBetween(from, to);
        if (findAll.isEmpty()) {
            logger.error("There are no any students between {} and {}", from, to);
            throw new StudentNotFoundException();
        }
        logger.info("Students between age {} and {} are {}", from, to, findAll);
        return findAll;
    }

    public Integer getStudentsCount() {
        logger.info("Was invoked method to get Number of students");
        Integer count = countRepo.getAllByIdIsNotNull();
        if (this.studentRepo.findAll().isEmpty()) { //to check
            logger.error("We have 0 Students");
            throw new StudentNotFoundException();
        }
        logger.info("There are {} students", count);
        return count;
    }

    public Double getAverageAge() {
        logger.info("Was invoked method of average Age");
        Double avg = countRepo.getAverageAge();
        if (this.studentRepo.findAll().isEmpty()) { //to check
            logger.error("We have 0 Students");
            throw new StudentNotFoundException();
        }
        logger.info("Average age of students is {}", avg);
        return avg;
    }

    public List<Student> getStudGreaterIdDesc5() {
        logger.info("Was invoked method of 5 last stud by id");
        List<Student> studentList = countRepo.getStudentByAgeIsGreaterThanOrderById();
        if (this.studentRepo.findAll().isEmpty()) { //to check
            logger.error("We have 0 Students");
            throw new StudentNotFoundException();
        }
        logger.info("Last 5 students by id : {} ", studentList);
        return studentList;
    }

    @Override
    public Collection<Student> findAllSortedByA() {
        List<Student> colStud = studentRepo.findAll().stream()
                .filter(student -> student.getName().startsWith("A"))
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
        return colStud;
    }

    @Override
    public Double streamGetAverageAge() {
        return studentRepo.findAll().stream()
                .mapToInt(Student::getAge)
                .average().orElseThrow();
    }

    @Override
    public void print6StudentsNameInThreadNotSync() {
        logger.info("Was invoked method for thread non synchronized");
        List<Student> list = this.studentRepo.findAll();
        System.out.println(list.get(0).getName());
        System.out.println(list.get(1).getName());
        new Thread(() -> {
            System.out.println(list.get(2).getName());
            System.out.println(list.get(3).getName());
        }).start();
        new Thread(() -> {
            System.out.println(list.get(4).getName());
            System.out.println(list.get(5).getName());
        }).start();
    }

    @Override
    public void print6StudentsNameSynchronized() {
        logger.info("Was invoked method for Synchronized");
        List<Student> list = studentRepo.findAll();
        System.out.println(list.get(0).getName());
        System.out.println(list.get(1).getName());
        new Thread(() -> {
            firstTwoSync(list.get(2).getName(), list.get(3).getName());
        }).start();
        new Thread(() -> {
            secondTwoSync(list.get(4).getName(), list.get(5).getName());
        }).start();
    }

    private void firstTwoSync(String name1, String name2) {
        synchronized (lock) {
            System.out.println(name1);
            System.out.println(name2);
        }
    }

    private void secondTwoSync(String name1, String name2) {
        synchronized (lock) {
            System.out.println(name1);
            System.out.println(name2);
        }
    }
}
