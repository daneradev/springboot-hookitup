package com.hookitup.Consumer.controller;

import com.hookitup.Consumer.kafka.KafkaConfigApp;
import com.hookitup.Consumer.model.consumer.OfferByConsumer;
import com.hookitup.Consumer.model.consumer.OfferByShotId;
import com.hookitup.Consumer.model.deal.Deal;
import com.hookitup.Consumer.service.DeleteAllOffersRelatedToDeal;
import com.hookitup.Consumer.service.MessageProducerService;
import com.hookitup.Consumer.service.OfferByConsumerService;
import com.hookitup.Consumer.service.OfferByShotIdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/deal")
@Slf4j
public class DealControl {

    @Autowired
    private OfferByConsumerService offerByConsumerService;

    @Autowired
    private OfferByShotIdService offerByShotIdService;

    @Autowired
    private MessageProducerService messageProducerService;

    @Autowired
    private DeleteAllOffersRelatedToDeal deleteAllOffersRelatedToDeal;

    @Autowired
    private KafkaConfigApp kafkaConfigApp;

    @PreAuthorize("hasAuthority('SCOPE_CONSUMER')")
    @PostMapping(path = "/makeDeal")
    public ResponseEntity<String> makeDeal(
            @RequestBody Deal deal,
            JwtAuthenticationToken auth) {

        deal.setConsumerId(auth.getName());
        deal.setConsumerName(auth.getToken().getClaimAsString("email"));
        deal.setIsActive(true);

        Optional<OfferByConsumer> offerByConsumer = offerByConsumerService
                .findByConsumer(
                        deal.getConsumerId(),
                        deal.getOfferPublishDate(),
                        deal.getOfferId()
                );

        if (offerByConsumer.isEmpty())
            return ResponseEntity.notFound().build();

        deleteAllOffersRelatedToDeal.delete(deal);

        messageProducerService.sendMessage(
                kafkaConfigApp.getStartDealByConsumerOut(),
                deal.turnClassIntoMessageString(kafkaConfigApp.getMessageDivider())
        );

        return ResponseEntity.ok("ok");

    }
}
