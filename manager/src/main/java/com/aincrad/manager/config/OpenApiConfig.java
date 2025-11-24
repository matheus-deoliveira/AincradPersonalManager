package com.aincrad.manager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Aincrad Personal Manager API")
                        .version("1.0")
                        .description("API para gamificação de tarefas inspirada em Sword Art Online.")
                        .contact(new Contact()
                                .name("Matheus de Oliveira")
                                .url("https://github.com/matheus-deoliveira"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}