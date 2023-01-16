package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hogwarts.school.model.Faculty;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repo.FacultyRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private FacultyRepo facultyRepo;

    public FacultyServiceImpl(FacultyRepo facultyRepo) {
        this.facultyRepo = facultyRepo;
    }

    private final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    @Override
    public Faculty addFaculty(Faculty faculty) {
        logger.info("Was invoked method create faculty");
        return this.facultyRepo.save(faculty);
    }

    @Override
    public Faculty findFaculty(long id) {
        logger.info("Was invoked method find faculty by id {}", id);
        Faculty findFac = this.facultyRepo.findById(id).get();
        logger.info("Faculty with id {} is {}", id, findFac);
        return findFac;
    }

    @Override
    public Faculty editFaculty(long id, Faculty faculty) {
        logger.info("Was invoked method edit faculty by id {}", id);
        Faculty editFac = this.facultyRepo.save(faculty);
        logger.info("Faculty {} was successfully edit ", id);
        return editFac;
    }

    @Override
    public void deleteFaculty(long id) {
        logger.info("Was invoked method delete faculty by id");
        this.facultyRepo.deleteById(id);
        logger.info("Faculty with id {} was deleted", id);
    }

    @Override
    public Collection<Faculty> findByColor(String color) {
        logger.info("Was invoked method find faculty by color");
        Collection<Faculty> byColor = this.facultyRepo.findByColor(color);
        logger.info("Faculty by color {} is {}", color, byColor);
        return byColor;
    }

    @Override
    public Collection<Faculty> getAllFac() {
        logger.info("Was invoked method for all faculties");
        Collection<Faculty> allFac = this.facultyRepo.findAll();
        logger.info("All faculties: {}", allFac);
        return allFac;
    }

    @Override
    public Collection<Faculty> findFacultyByNameIgnoreCaseOrColorIgnoreCase(String name, String color) {
        logger.info("Was invoked method for faculties by name or color");
        Collection<Faculty> facByNameOrColor = this.facultyRepo.findFacultyByNameIgnoreCaseOrColorIgnoreCase(name, color);
        logger.info("Fac by name {} or color is {}", name, color);
        return facByNameOrColor;
    }

    @Override
    public Faculty findFacBiggerLength() {
        return facultyRepo.findAll().stream()
                .max(Comparator.comparingInt(f -> f.getName().length()))
                .orElseThrow();
    }

}
