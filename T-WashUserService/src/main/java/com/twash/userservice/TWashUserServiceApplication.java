package com.twash.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="T-WashUsersService", version="1.0",description="UsersService"))
@EnableSwagger2
public class TWashUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TWashUserServiceApplication.class, args);
	}

}
