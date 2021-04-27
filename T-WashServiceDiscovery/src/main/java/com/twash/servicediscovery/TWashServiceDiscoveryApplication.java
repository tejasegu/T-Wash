package com.twash.servicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class TWashServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TWashServiceDiscoveryApplication.class, args);
	}

}
