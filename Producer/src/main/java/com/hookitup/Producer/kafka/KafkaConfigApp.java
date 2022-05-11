package com.hookitup.Producer.kafka;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class KafkaConfigApp {
    @Value("${my-kafka.divider}")
    private String messageDivider;

    @Value("${my-kafka.bindings.deal-started-by-producer}")
    private String dealStartedByProducerOut;

    @Value("${my-kafka.bindings.decline-offer}")
    private String declineOfferOut;
}
