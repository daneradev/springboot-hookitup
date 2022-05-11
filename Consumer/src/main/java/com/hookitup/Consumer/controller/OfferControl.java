package com.hookitup.Consumer.controller;

import com.hookitup.Consumer.kafka.KafkaConfigApp;
import com.hookitup.Consumer.model.consumer.Offer;
import com.hookitup.Consumer.model.consumer.OfferByConsumer;
import com.hookitup.Consumer.model.consumer.OfferByShotId;
import com.hookitup.Consumer.model.producer.ShotByProducerUtil;
import com.hookitup.Consumer.service.MessageProducerService;
import com.hookitup.Consumer.service.OfferByConsumerService;
import com.hookitup.Consumer.service.OfferByShotIdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/offer")
@Slf4j
public class OfferControl {

    @Autowired
    private OfferByConsumerService offerByConsumerService;

    @Autowired
    private OfferByShotIdService offerByShotIdService;

    @Autowired
    private MessageProducerService messageProducerService;

    @Autowired
    private KafkaConfigApp kafkaConfigApp;

    @PreAuthorize("hasAuthority('SCOPE_CONSUMER')")
    @PostMapping(path = "/insert")
    public ResponseEntity<String> insertOffer(
            @RequestBody Offer offer,
            JwtAuthenticationToken auth) {

        offer.setConsumerId(auth.getName());
        offer.setConsumerName(auth.getToken().getClaimAsString("email"));
        offer.setOfferPublishDate(LocalDateTime.now());
        offer.setOfferId(UUID.randomUUID().toString());

        offerByConsumerService.insert(new OfferByConsumer(offer));
        offerByShotIdService.insert(new OfferByShotId(offer));

        ShotByProducerUtil shotByProducerUtil = new ShotByProducerUtil(offer);

        messageProducerService.sendMessage(
                kafkaConfigApp.getSumNumberOffersOut(),
                shotByProducerUtil.turnClassIntoMessageString(kafkaConfigApp.getMessageDivider())
        );

        return ResponseEntity.ok("ok");
    }


    @PreAuthorize("hasAuthority('SCOPE_CONSUMER')")
    @DeleteMapping
    public ResponseEntity<String> deleteOffer(
            @RequestBody Offer offer,
            JwtAuthenticationToken auth) {

        offer.setConsumerId(auth.getName());

        Optional<OfferByConsumer> offerByConsumer = offerByConsumerService
                .findByConsumer(
                        offer.getConsumerId(),
                        offer.getOfferPublishDate(),
                        offer.getOfferId()
                );

        if (offerByConsumer.isEmpty())
            return ResponseEntity.notFound().build();

        offerByConsumerService.delete(offerByConsumer.get());
        offerByShotIdService.delete(new OfferByShotId(offer));

        ShotByProducerUtil shotByProducerUtil = new ShotByProducerUtil(offer);

        messageProducerService.sendMessage(
                kafkaConfigApp.getSubtractNumberOffersOut(),
                shotByProducerUtil.turnClassIntoMessageString(kafkaConfigApp.getMessageDivider())
        );

        return ResponseEntity.ok("ok");
    }

}
