package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long count = 0;

    @Override

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(count++);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    @Override

    public Faculty findFaculty(long id) {
        return faculties.get(id);
    }

    @Override

    public Faculty editFaculty(long id, Faculty faculty) {
        if (!faculties.containsKey(id)) {
            return null;
        }
        faculties.put(id, faculty);
        return faculty;
    }

    @Override

    public void deleteFaculty(long id) {
        faculties.remove(id);
    }

    @Override

    public Collection<Faculty> findByColor(String color) {
        ArrayList<Faculty> result = new ArrayList<>();
        for (Faculty faculty : faculties.values()) {
            if (Objects.equals(faculty.getColor(), color)) {
                result.add(faculty);
            }
        }
        return result;
    }
}
