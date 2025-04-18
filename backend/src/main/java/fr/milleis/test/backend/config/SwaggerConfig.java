package fr.milleis.test.backend.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Swagger/OpenAPI documentation
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configure OpenAPI documentation
     * @return OpenAPI configuration
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product and Order Management API")
                        .version("1.0")
                        .description("API for managing products and orders in Java 17 and Spring Boot")
                        .contact(new Contact()
                                .name("Example Developer")
                                .email("dev@example.com")));
    }
}