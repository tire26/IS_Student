package com.example.ais_cdo_university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@SpringBootApplication
@Import(RepositoryRestMvcConfiguration.class)
@Configuration
@EnableJpaRepositories
public class Application  {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}