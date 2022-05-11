package com.hookitup.DealHandler.kafka;

import com.hookitup.DealHandler.model.consumer.ConsumerDeal;
import com.hookitup.DealHandler.model.deal.Deal;
import com.hookitup.DealHandler.model.producer.ProducerDeal;
import com.hookitup.DealHandler.service.ConsumerDealService;
import com.hookitup.DealHandler.service.ProducerDealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Slf4j
public class Functions {

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private ProducerDealService producerDealService;

    @Autowired
    private ConsumerDealService consumerDealService;

    @Bean
    public Function<String, String> closeDeal() {
        return input -> {

            Deal deal = new Deal(input, kafkaService.getMessageDivider());

            producerDealService.insertProducerDeal(
                    new ProducerDeal(deal)
            );

            consumerDealService.insertConsumerDeal(
                    new ConsumerDeal(deal)
            );

            return input;
        };
    }
}
