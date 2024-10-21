package com.geralab.JavaExam;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class JavaExamApplication {

	@PostConstruct
	void init() {
		System.out.println("******MAIN CONFIG");
	}

	public static void main(String[] args) {
		SpringApplication.run(JavaExamApplication.class, args);
	}

}
