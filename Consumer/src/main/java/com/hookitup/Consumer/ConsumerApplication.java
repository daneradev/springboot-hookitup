package com.hookitup.Consumer;

import com.hookitup.Consumer.model.consumer.OfferByConsumer;
import com.hookitup.Consumer.model.consumer.OfferByShotId;
import com.hookitup.Consumer.repository.OfferByConsumerRepo;
import com.hookitup.Consumer.repository.OfferByShotIdRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableEurekaClient
@Slf4j
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			OfferByConsumerRepo offerByConsumerRepo,
			OfferByShotIdRepo offerByShotIdRepo
	) {
		return args -> {

			OfferByConsumer offerByConsumer = OfferByConsumer.builder()
					.consumerId("consumer")
					.publishDate(LocalDateTime.parse("2022-04-30T11:04:16.008"))
					.offerId("uuid1")
					.consumerName("consumer1")
					.producerId("producer1")
					.shotId("uuid1")
					.product("cellphone")
					.price(300)
					.build();

			OfferByShotId offerByShotId = OfferByShotId.builder()
					.producerId("producer1")
					.shotId("uuid1")
					.publishDate(LocalDateTime.parse("2022-04-30T11:04:16.008"))
					.consumerId("consumer")
					.consumerName("consumer1")
					.offerId("uuid1")
					.price(300)
					.build();

			offerByConsumerRepo.insert(offerByConsumer);
			offerByShotIdRepo.insert(offerByShotId);
		};
	}

}
