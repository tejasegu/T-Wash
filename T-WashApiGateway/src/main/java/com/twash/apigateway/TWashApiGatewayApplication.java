package com.twash.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient

@EnableCircuitBreaker
public class TWashApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TWashApiGatewayApplication.class, args);
	}

}
