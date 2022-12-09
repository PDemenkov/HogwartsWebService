package ru.hogwarts.school.service;

import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long count = 0;

    @Override
    public Faculty addFaculty(Faculty faculty) {
        long newId = count++;
        faculty.setId(newId);
        faculties.put(newId, faculty);
        return faculty;
    }

    @Override
    public Faculty findFaculty(long id) {
        if (this.faculties.containsKey(id)) {
            return faculties.get(id);
        } else {
            throw new FacultyNotFoundException();
        }
    }

    @Override
    public Faculty editFaculty(long id, Faculty faculty) {
        if (!faculties.containsKey(id)) {
            throw new FacultyNotFoundException();
        }
        Faculty oldFac = this.faculties.get(id);
        oldFac.setName(faculty.getName());
        oldFac.setColor(faculty.getColor());
        return oldFac;
    }

    @Override
    public void deleteFaculty(long id) {
        if (this.faculties.containsKey(id)) {
            this.faculties.remove(id);
        } else {
            throw new FacultyNotFoundException();
        }
    }

    @Override
    public Collection<Faculty> findByColor(String color) {
        return this.faculties.values().stream()
                .filter(s->s.getColor().equals(color))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Faculty> getAllFac() {
        return faculties.values();
    }
}
