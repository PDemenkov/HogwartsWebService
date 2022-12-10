package ru.hogwarts.school.service;

import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    private long count = 0;


    @Override
    public Student addStudent(Student student) {
        long newId = this.count++;
            student.setId(newId);
            students.put(newId, student);
            return student;
        }

    @Override
    public Student findStudent(long id) {
        if (this.students.containsKey(id)) {
            return students.get(id);
        } else {
            throw new StudentNotFoundException();
        }
    }

    @Override
    public Student editStudent(long id, Student student) {
        if (!students.containsKey(id)) {
            throw new StudentNotFoundException();
        }
        Student oldStud = this.students.get(id);
        oldStud.setAge(student.getAge());
        oldStud.setName(student.getName());
        return oldStud;
    }

    @Override
    public void deleteStudent(long id) {
        if (this.students.containsKey(id)) {
            this.students.remove(id);
        } else {
            throw new StudentNotFoundException();
        }
    }

    @Override
    public Collection<Student> findByAge(int age) {
        return this.students.values().stream()
                .filter(s->s.getAge()==age)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getAllStud() {
        return students.values();
    }
}
