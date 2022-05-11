package com.hookitup.Consumer.controller;

import com.hookitup.Consumer.model.consumer.OfferByConsumer;
import com.hookitup.Consumer.service.OfferByConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/offerByConsumer")
public class OfferByConsumerControl {

    @Autowired
    private OfferByConsumerService offerByConsumerService;

    @PreAuthorize("hasAuthority('SCOPE_CONSUMER')")
    @GetMapping
    public ResponseEntity<List<OfferByConsumer>> findAllOfferByConsumer(JwtAuthenticationToken auth) {
        return ResponseEntity.ok(offerByConsumerService.findAllByConsumer(auth.getName()));
    }


}
