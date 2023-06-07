package com.example.RESTAPILearning.crudDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class CrudDemoApplication extends SpringBootServletInitializer {

	//Needed for war deployment
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(CrudDemoApplication.class);
	}
	public static void main(String[] args) {

		SpringApplication.run(CrudDemoApplication.class, args);
	}

}
