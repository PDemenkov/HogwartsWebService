package ru.hogwarts.school.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI schoolApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("School API")
                        .version("1.0.0")
                        .description("API to manage Hogwarts"))
                .addTagsItem(new Tag().name("student").description("Student management API"))
                .addTagsItem(new Tag().name("faculty").description("Faculty management API"))
                .addTagsItem(new Tag().name("avatar").description("Set avatar to student"));
    }
}
