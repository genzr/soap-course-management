package com.in28minutes.soapcoursemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.in28minutes.courses","com.in28minutes.soapcoursemanagement.soap"})
public class SoapCourseManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapCourseManagementApplication.class, args);
	}

}
