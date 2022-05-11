package com.hookitup.Producer;

import com.hookitup.Producer.repository.ShotByIdRepo;
import com.hookitup.Producer.repository.ShotByProducerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableEurekaClient
@Slf4j
public class ProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}


	@Bean
	public CommandLineRunner commandLineRunner(
			ShotByProducerRepo shotByProducerRepo,
			ShotByIdRepo shotByIdRepo
	) {
		return args -> {
			/*
			ShotByProducer shotByProducer = ShotByProducer.builder()
					.producerId("producer1")
					.publishDate(LocalDateTime.parse("2021-04-30T11:04:16.008"))
					.shotId("uuid1")
					.producerName("producer1")
					.title("my shot 1")
					.description("shot 1")
					.product("cellphone")
					.amount(2)
					.price(200)
					.numberOffers(0)
					.build();

			ShotById shotById = ShotById.builder()
					.shotId("uuid1")
					.publishDate(LocalDateTime.parse("2021-04-30T11:04:16.008"))
					.producerId("producer1")
					.producerName("producer1")
					.title("my shot 1")
					.description("shot 1")
					.product("cellphone")
					.amount(2)
					.price(200)
					.build();

			shotByProducerRepo.insert(shotByProducer);
			shotByIdRepo.insert(shotById);

			 */
		};

	}

}
