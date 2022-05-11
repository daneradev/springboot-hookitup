package com.hookitup.GatewayServerHUP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;

@SpringBootApplication
@EnableEurekaClient
@EnableWebFluxSecurity
public class GatewayServerHupApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerHupApplication.class, args);
	}

	@Bean
	public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
		http.authorizeExchange()
				.pathMatchers("/producer/actuator/**").permitAll()
				.pathMatchers("/consumer/actuator/**").permitAll()
				.anyExchange().authenticated()
				.and()
				.oauth2Login();
		http
				.csrf().disable();

		return  http.build();
	}
}
