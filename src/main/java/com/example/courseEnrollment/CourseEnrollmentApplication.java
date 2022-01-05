package com.example.courseEnrollment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.example.courseEnrollment.controller",
		"com.example.courseEnrollment.service","com.example.courseEnrollment.aop", "com.example.courseEnrollment.validator",
		"com.example.courseEnrollment.security"})
@EnableJpaRepositories("com.example.courseEnrollment.repository")
@EntityScan("com.example.courseEnrollment.model")
public class CourseEnrollmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseEnrollmentApplication.class, args);
	}

}
