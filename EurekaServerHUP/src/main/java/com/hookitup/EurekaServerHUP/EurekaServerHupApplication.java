package com.hookitup.EurekaServerHUP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerHupApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerHupApplication.class, args);
	}

}
