package com.hookitup.Consumer.kafka;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class KafkaConfigApp {
    @Value("${my-kafka.divider}")
    private String messageDivider;

    @Value("${my-kafka.bindings.sumNumberOffers}")
    private String sumNumberOffersOut;

    @Value("${my-kafka.bindings.subtractNumberOffers}")
    private String subtractNumberOffersOut;

    @Value("${my-kafka.bindings.deal-started-by-consumer}")
    private String startDealByConsumerOut;
}
