package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ru.hogwarts.school.model.Faculty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Returns a faculty by id", tags = "faculty")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Faculty model"),
            @ApiResponse(responseCode = "404", description = "Faculty not found")
    })
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    @Operation(summary = "Create a new Faculty", tags = "faculty")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Created faculty"),
            @ApiResponse(responseCode = "400", description = "Bad request. Faculty name and color must be String")
    })
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Return All Faculties", tags = "faculty")
    @ApiResponse(responseCode = "200", description = "List of all faculties")
    public Collection<Faculty> getAllFac() {
        return facultyService.getAllFac();
    }

    @PutMapping
    @Operation(summary = "Update Faculty", tags = "faculty")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Faculty has been updated"),
            @ApiResponse(responseCode = "404", description = "Faculty not found")
    })
    public Faculty editFaculty(@RequestBody Faculty faculty, Long id) {
        return this.facultyService.editFaculty(id, faculty);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete Faculty", tags = "faculty")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Faculty has been deleted"),
            @ApiResponse(responseCode = "404", description = "Faculty not found")
    })
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Find faculty by color", tags = "faculty")
    @ApiResponse(responseCode = "200", description = "List of faculties of this color")
    public Collection<Faculty> findFaculties(@RequestParam(required = false) String color) {
        return this.facultyService.findByColor(color);
    }

    @GetMapping("/search/fac")
    @Operation(summary = "Find fac by name or color",tags = "faculty")
    public ResponseEntity<Collection<Faculty>> FindFaculty(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String color) {
        return ResponseEntity.ok(facultyService.findFacultyByNameIgnoreCaseOrColorIgnoreCase(name, color));
    }

    @GetMapping("/search/students{id}")
    @Operation(summary = "Получить у деканата список студентов факультета",tags = "faculty")
    public Collection<Student> findStudentsFaculty(@PathVariable Long id) {
        return   facultyService.findFaculty(id).getStudents();
    }
}
