package io.gupy.sw;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "io.gupy.sw" })
@SpringBootApplication
public class StarWarsApplication {
    
    public static void main(String[] args) throws IOException {
        SpringApplication.run(StarWarsApplication.class, args);
    }
}