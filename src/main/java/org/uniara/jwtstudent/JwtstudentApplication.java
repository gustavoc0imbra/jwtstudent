package org.uniara.jwtstudent;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API User/Student", version = "1.0", description = "API REST Users/Student with JWT auth"))
public class JwtstudentApplication {

	public static void main(String[] args) {
		System.out.println("Olá");
		SpringApplication.run(JwtstudentApplication.class, args);
	}

}
