package com.cantvas2.cantvas2;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cantvas2.cantvas2.models.Course;
import com.cantvas2.cantvas2.models.Student;
import com.cantvas2.cantvas2.services.CantvasUserService;
import com.cantvas2.cantvas2.services.DatabaseService;

@SpringBootApplication
public class Cantvas2Application {

	public static void main(String[] args) {
		SpringApplication.run(Cantvas2Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(DatabaseService databaseService, CantvasUserService cantvasUserService){
		return args -> {
			List<Course> coursesList = List.of(new Course("Java 401", "Advanced Java course with Spring and Android"),
			new Course("JavaScript 401", "Advanced JavaScript course going deep into React and Node.js"),
			new Course("JavaScript 201", "Introductory JavaScript"));
			databaseService.saveAll(coursesList.get(0));

			Student student = new Student("Bingbong");
			cantvasUserService.saveOne(student);
		};
	}

}
