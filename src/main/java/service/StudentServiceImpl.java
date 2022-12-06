package service;

import model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class StudentServiceImpl implements StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    private long count = 0;

    @Override
    public Student addStudent(Student student) {
        student.setId(count++);
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Student findStudent(long id) {
        return students.get(id);
    }

    @Override
    public Student editStudent(long id, Student student) {
        if (!students.containsKey(id)) {
            return null;
        }
        students.put(id, student);
        return student;
    }

    @Override
    public void deleteStudent(long id) {
        students.remove(id);
    }

    @Override
    public Collection<Student> findByAge(int age) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }
}
