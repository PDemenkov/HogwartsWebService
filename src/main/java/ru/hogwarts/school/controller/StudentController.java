package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentServiceImpl studentService) {
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
    @Operation(summary = "Create a new Student", tags = "student")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Created student"),
            @ApiResponse(responseCode = "400", description = "Bad request. Student consist of type String")
    })
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Returns list of All students", tags = "student")
    @ApiResponse(responseCode = "200", description = "List of all students")
    public Collection<Student> getAllStud() {
        return studentService.getAllStud();
    }

    @PutMapping("{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student has been updated"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @Operation(summary = "Update student by id", tags = "student")
    public Student editStudent(@RequestBody Student student, @PathVariable Long id) {
        return this.studentService.editStudent(id, student);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete student by id", tags = "student")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student has been deleted"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/age/{age}")
    @Operation(summary = "Return students by age", tags = "student")
    @ApiResponse(responseCode = "200", description = "List of students in this age")
    public Collection<Student> findStudents(@PathVariable int age) {
        return this.studentService.findByAge(age);
    }

    @GetMapping
    @Operation(summary = "Return students Between age", tags = "student")
    @ApiResponse(responseCode = "200", description = "List of students between age")
    public Collection<Student> findAllByAgeBetween(@RequestParam int from, @RequestParam int to) {
        return this.studentService.findAllByAgeBetween(from, to);
    }

    @GetMapping("/search{id}")
    @Operation(summary = "Find in which faculty this student is in", tags = "student")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "Student does not exist"),
            @ApiResponse(responseCode = "200", description = "Schema of Faculty")
    })
    public Faculty findFacultyStudents(@PathVariable Long id) {
        return studentService.findStudent(id).getFaculty();
    }

    @GetMapping("/search/letterA")
    @Operation(summary = "Find all studs which name starts with 'A' by stream",tags = "student")
    public Collection<Student> findAllSortedByAStream() {
        return studentService.findAllSortedByA();
    }

    @GetMapping("/search/avgAge")
    @Operation(summary = "Average age of all students by stream", tags = "student")
    public Double streamGetAverageAgeStream() {
        return studentService.streamGetAverageAge();
    }

    @GetMapping("/thread/1")
    @Operation(summary = "first 6 stud non sync with 2 threads")
    public void nonSync() {
        this.studentService.print6StudentsNameInThreadNotSync();
    }

    @GetMapping("/thread/2")
    @Operation(summary = "first 6 stud sync with 2 threads")
    public void Sync() {
        this.studentService.print6StudentsNameSynchronized();
    }
}
