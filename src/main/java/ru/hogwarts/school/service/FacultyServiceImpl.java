package ru.hogwarts.school.service;

import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repo.FacultyRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private FacultyRepo facultyRepo;

    public FacultyServiceImpl(FacultyRepo facultyRepo) {
        this.facultyRepo = facultyRepo;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        return this.facultyRepo.save(faculty);
    }

    @Override
    public Faculty findFaculty(long id) {
        return this.facultyRepo.findById(id).get();
    }

    @Override
    public Faculty editFaculty(long id, Faculty faculty) {
        return this.facultyRepo.save(faculty);
    }

    @Override
    public void deleteFaculty(long id) {
        facultyRepo.deleteById(id);
    }

    @Override
    public Collection<Faculty> findByColor(String color) {
        return this.facultyRepo.findByColor(color);
    }

    @Override
    public Collection<Faculty> getAllFac() {
        return this.facultyRepo.findAll();
    }
}
