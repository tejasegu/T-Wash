package com.twash.bookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="T-WashBookingService", version="1.0",description="BookingService"))
@EnableSwagger2
@EnableEurekaClient
public class TWashBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TWashBookingServiceApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
