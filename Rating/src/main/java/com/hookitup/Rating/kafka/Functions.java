package com.hookitup.Rating.kafka;

import com.hookitup.Rating.model.consumer.ConsumerApp;
import com.hookitup.Rating.model.consumer.PendingRatingC;
import com.hookitup.Rating.model.deal.Deal;
import com.hookitup.Rating.model.producer.PendingRatingP;
import com.hookitup.Rating.model.producer.Producer;
import com.hookitup.Rating.service.consumer.ConsumerService;
import com.hookitup.Rating.service.consumer.ConsumerPendingRatingService;
import com.hookitup.Rating.service.producer.ProducerPendingRatingService;
import com.hookitup.Rating.service.producer.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;

@Service
@Slf4j
public class Functions {

    @Autowired
    private KafkaConfigApp kafkaConfigApp;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private ConsumerPendingRatingService consumerPendingRatingService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ProducerPendingRatingService producerPendingRatingService;

    @Bean
    public Consumer<String> pendingRating() {
        return input -> {
            Deal deal = new Deal(input, kafkaConfigApp.getMessageDivider());
            if (deal.getIsActive()) {
                createConsumerPending(deal);
                createProducerPending(deal);
            }

        };
    }

    private void createConsumerPending(Deal deal) {
        PendingRatingC pendingRatingC = PendingRatingC.builder()
                .producerId(deal.getProducerId())
                .producerName(deal.getProducerName())
                .build();

        Optional<ConsumerApp> consumer = consumerService.findById(deal.getConsumerId());

        if (consumer.isEmpty()) {
            ConsumerApp newConsumer = ConsumerApp.builder()
                    .id(deal.getConsumerId())
                    .numberRatings(0)
                    .rating(0)
                    .build();
            consumerService.save(newConsumer);

            pendingRatingC.setConsumer(newConsumer);
            consumerPendingRatingService.save(pendingRatingC);
            return;
        }

        pendingRatingC.setConsumer(consumer.get());
        consumerPendingRatingService.save(pendingRatingC);
    }

    private void createProducerPending(Deal deal) {

        PendingRatingP pendingRatingP = PendingRatingP.builder()
                .consumerId(deal.getConsumerId())
                .consumerName(deal.getConsumerName())
                .build();

        Optional<Producer> producer = producerService.findById(deal.getProducerId());

        if (producer.isEmpty()) {
            Producer newProducer = Producer.builder()
                    .id(deal.getProducerId())
                    .rating(0)
                    .numberRatings(0)
                    .build();
            producerService.save(newProducer);
            pendingRatingP.setProducer(newProducer);
            producerPendingRatingService.save(pendingRatingP);
            return;
        }

        pendingRatingP.setProducer(producer.get());
        producerPendingRatingService.save(pendingRatingP);
    }

}
