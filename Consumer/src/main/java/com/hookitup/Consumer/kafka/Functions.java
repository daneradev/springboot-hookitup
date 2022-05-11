package com.hookitup.Consumer.kafka;

import com.hookitup.Consumer.model.consumer.OfferDeclined;
import com.hookitup.Consumer.model.consumer.OfferByConsumer;
import com.hookitup.Consumer.model.consumer.OfferByShotId;
import com.hookitup.Consumer.model.consumer.OfferDeclinedEntity;
import com.hookitup.Consumer.model.deal.Deal;
import com.hookitup.Consumer.service.DeleteAllOffersRelatedToDeal;
import com.hookitup.Consumer.service.OfferByConsumerService;
import com.hookitup.Consumer.service.OfferByShotIdService;
import com.hookitup.Consumer.service.OfferDeclinedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
@Slf4j
public class Functions {

    @Autowired
    private OfferByConsumerService offerByConsumerService;

    @Autowired
    private OfferByShotIdService offerByShotIdService;

    @Autowired
    private OfferDeclinedService offerDeclinedService;

    @Autowired
    private DeleteAllOffersRelatedToDeal deleteAllOffersRelatedToDeal;

    @Autowired
    private KafkaConfigApp kafkaConfigApp;

    @Bean
    public Function<String, String> dealStartedByProducer() {
        return input -> {

                    Deal deal = new Deal(input, kafkaConfigApp.getMessageDivider());

                    Optional<OfferByConsumer> consumer = offerByConsumerService.findByConsumer(
                            deal.getConsumerId(),
                            deal.getOfferPublishDate(),
                            deal.getOfferId()
                    );

                    if (consumer.isEmpty()) {
                        deal.setIsActive(false);
                        return deal.turnClassIntoMessageString(kafkaConfigApp.getMessageDivider());
                    }

                    deleteAllOffersRelatedToDeal.delete(deal);

                    return input;
                };
    }

    @Bean
    public Consumer<String> offerDeclined() {
        return input -> {

                    OfferDeclined offer = new OfferDeclined(input, kafkaConfigApp.getMessageDivider());
                    OfferDeclinedEntity offerDeclinedEntity = new OfferDeclinedEntity(offer);

                    offerByConsumerService
                            .delete(new OfferByConsumer(offer));

                    offerByShotIdService
                            .delete(new OfferByShotId(offer));

                    offerDeclinedService.insertOfferDeclined(offerDeclinedEntity);
                };
    }


}
