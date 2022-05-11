package com.hookitup.Producer.controller;

import com.hookitup.Producer.kafka.KafkaConfigApp;
import com.hookitup.Producer.model.consumer.OfferDeclined;
import com.hookitup.Producer.model.deal.Deal;
import com.hookitup.Producer.model.producer.ShotById;
import com.hookitup.Producer.model.producer.ShotByProducer;
import com.hookitup.Producer.service.MessageConsumerService;
import com.hookitup.Producer.service.ShotByIdService;
import com.hookitup.Producer.service.ShotByProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/deal")
public class DealControl {

    @Autowired
    private ShotByIdService shotByIdService;

    @Autowired
    private ShotByProducerService shotByProducerService;

    @Autowired
    private MessageConsumerService messageConsumerService;

    @Autowired
    private KafkaConfigApp kafkaConfigApp;


    @PreAuthorize("hasAuthority('SCOPE_PRODUCER')")
    @DeleteMapping(path = "/makeDeal")
    public ResponseEntity<String> makeDeal(
            @RequestBody Deal deal,
            JwtAuthenticationToken auth) {

        deal.setProducerId(auth.getName());
        deal.setProducerName(auth.getToken().getClaimAsString("email"));
        deal.setIsActive(true);

        Optional<ShotByProducer> shotByProducer = shotByProducerService
                .findByShot(deal.getProducerId(),
                        deal.getShotPublishDate(),
                        deal.getShotId());

        if (shotByProducer.isEmpty())
            return ResponseEntity.notFound().build();


        shotByProducerService.deleteShot(new ShotByProducer(deal));
        shotByIdService.deleteShotById(new ShotById(deal));

        messageConsumerService.sendMessage(
                kafkaConfigApp.getDealStartedByProducerOut(),
                deal.turnClassIntoMessageString(kafkaConfigApp.getMessageDivider())
        );

        return ResponseEntity.ok("deal in process");
    }


    @PreAuthorize("hasAuthority('SCOPE_PRODUCER')")
    @PostMapping(path = "/declineOffer")
    public ResponseEntity<String> declineOffer(
            @RequestBody OfferDeclined offer,
            JwtAuthenticationToken auth) {

        offer.setProducerId(auth.getName());

        Optional<ShotByProducer> shotByProducer = shotByProducerService.findByShot(
                offer.getProducerId(),
                offer.getShotPublishDate(),
                offer.getShotId()
        );

        if (shotByProducer.isEmpty())
            return ResponseEntity.notFound().build();

        shotByProducer.get().subtractNumberOffers();
        shotByProducerService.insertShot(shotByProducer.get());

        offer.setDecliningDate();
        messageConsumerService.sendMessage(
                kafkaConfigApp.getDeclineOfferOut(),
                offer.turnClassIntoMessageString(kafkaConfigApp.getMessageDivider())
        );

        return ResponseEntity.ok("turning down offer");
    }
}
