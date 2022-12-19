package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/counter")
public class CountController {

    StudentServiceImpl studentService;

    public CountController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/countStud")
    @Operation(summary = "get number of studs",tags = "counter")
    public Integer getStudCount() {
        return studentService.getStudentsCount();
    }

    @GetMapping("/averageAge")
    @Operation(summary = "get average age of studs",tags = "counter")
    public Double getAverageAge() {
        return studentService.getAverageAge();
    }


    @GetMapping("/get5StudByBiggerId")
    @Operation(summary = "get 5 stud with greater id",tags = "counter")
    public List<Student> getStudGreaterIdDesc5() {
        return studentService.getStudGreaterIdDesc5();
    }
}
