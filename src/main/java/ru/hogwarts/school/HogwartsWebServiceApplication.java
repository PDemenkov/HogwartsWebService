package ru.hogwarts.school;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class HogwartsWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HogwartsWebServiceApplication.class, args);
    }

}
