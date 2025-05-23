package com.example.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


import com.example.backend.business.services.AuthenticationService;
import com.example.backend.web.Models.RegisterRequest;
import com.example.backend.web.Models.Enum.Role;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	
	//executer une seul fois pour ajouter un admin au base apres mais en commentaire
	/*  @Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("admin")
					.lastname("admin")
					.email("admin@gmail.com")
					.password("admin")
					.role(Role.ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getToken());

			var teacher = RegisterRequest.builder()
					.firstname("teacher")
					.lastname("teacher")
					.email("teacher@gmail.com")
					.password("teacher")
					.role(Role.TEACHER)
					.build();
			System.out.println("Teacher token: " + service.register(teacher).getToken());

			var student = RegisterRequest.builder()
					.firstname("student")
					.lastname("student")
					.email("student@gmail.com")
					.password("student")
					.role(Role.STUDENT)
					.build();
			System.out.println("Student token: " + service.register(student).getToken());
		}; 
	} */

}
