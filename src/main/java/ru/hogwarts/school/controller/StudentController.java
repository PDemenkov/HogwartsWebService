package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ru.hogwarts.school.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Returns a student by id",
            tags = "student")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Student model",
                    content = @Content(schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "404",
                    description = "Student not found",
    content = @Content)})
    public Student getStudentInfo(@PathVariable Long id) {
        return this.studentService.findStudent(id);
    }

    @PostMapping()
    @Operation(summary = "Create a new Student",tags = "student")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Created student"),
            @ApiResponse(responseCode = "400",description = "Bad request. Student consist of type String")
    })
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Returns list of All students", tags = "student")
    @ApiResponse(responseCode = "200",description = "List of all students")
    public Collection<Student> getAllStud() {
        return studentService.getAllStud();
    }

    @PutMapping("{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Student has been updated"),
            @ApiResponse(responseCode = "404",description = "Student not found")
    })
    @Operation(summary = "Update student by id", tags = "student")
    public Student editStudent(@RequestBody Student student,@PathVariable Long id) {
        return this.studentService.editStudent(id, student);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete student by id",tags = "student")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student has been deleted"),
            @ApiResponse(responseCode = "404",description = "Student not found")
    })
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/age/{age}")
    @Operation(summary = "Return students by age",tags = "student")
    @ApiResponse(responseCode = "200",description = "List of students in this age")
    public Collection<Student> findStudents(@PathVariable int age) {
        return this.studentService.findByAge(age);
    }
}
