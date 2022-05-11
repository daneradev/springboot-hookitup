package com.hookitup.Rating.kafka;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class KafkaConfigApp {

    @Value("${my-kafka.divider}")
    private String messageDivider;
}
