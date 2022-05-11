package com.hookitup.Producer.kafka;


import com.hookitup.Producer.model.deal.Deal;
import com.hookitup.Producer.model.producer.ShotById;
import com.hookitup.Producer.model.producer.ShotByProducer;
import com.hookitup.Producer.model.producer.ShotByProducerUtil;
import com.hookitup.Producer.service.ShotByIdService;
import com.hookitup.Producer.service.ShotByProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
@Slf4j
public class Functions {

    @Autowired
    private ShotByIdService shotByIdService;

    @Autowired
    private ShotByProducerService shotByProducerService;

    @Autowired
    private KafkaConfigApp kafkaConfigApp;

    @Bean
    public Consumer<String> sumNumberOffers() {
        return input -> {

            ShotByProducerUtil shot = new ShotByProducerUtil(input, kafkaConfigApp.getMessageDivider());

            Optional<ShotByProducer> shotByProducer = shotByProducerService
                    .findByShot(
                            shot.getProducerId(),
                            shot.getPublishDate(),
                            shot.getShotId()
                    );
            if (shotByProducer.isPresent()) {
                shotByProducer.get().sumNumberOffers();
                shotByProducerService.insertShot(shotByProducer.get());
            }

        };
    }

    @Bean
    public Consumer<String> subtractNumberOffers() {
        return input -> {

            ShotByProducerUtil shot = new ShotByProducerUtil(input, kafkaConfigApp.getMessageDivider());

            Optional<ShotByProducer> shotByProducer = shotByProducerService
                    .findByShot(
                            shot.getProducerId(),
                            shot.getPublishDate(),
                            shot.getShotId()
                    );
            if (shotByProducer.isPresent()) {
                shotByProducer.get().subtractNumberOffers();
                shotByProducerService.insertShot(shotByProducer.get());
            }
        };
    }

    @Bean
    public Function<String, String> dealStartedByConsumer() {
        return input -> {

            Deal deal = new Deal(input, kafkaConfigApp.getMessageDivider());

            Optional<ShotByProducer> shotByProducer = shotByProducerService.findByShot(
                    deal.getProducerId(),
                    deal.getShotPublishDate(),
                    deal.getShotId()
            );

            if (shotByProducer.isEmpty()) {
                deal.setIsActive(false);
                return deal.turnClassIntoMessageString(kafkaConfigApp.getMessageDivider());
            }


            shotByProducerService.deleteShot(new ShotByProducer(deal));
            shotByIdService.deleteShotById(new ShotById(deal));

            return input;
        };
    }


}
