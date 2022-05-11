package com.hookitup.DealHandler.controller;

import com.hookitup.DealHandler.model.consumer.ConsumerDeal;
import com.hookitup.DealHandler.service.ConsumerDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/consumerDeal")
public class ConsumerDealControl {

    @Autowired
    private ConsumerDealService service;

    @PreAuthorize("hasAuthority('SCOPE_CONSUMER')")
    @GetMapping
    public ResponseEntity<List<ConsumerDeal>> findConsumerDeals(
            JwtAuthenticationToken auth) {
        return ResponseEntity.ok(service.findByIsActiveAndConsumerId(
                true,
                auth.getName()
        ));
    }

    @PreAuthorize("hasAuthority('SCOPE_CONSUMER')")
    @GetMapping(path = "/canceled")
    public ResponseEntity<List<ConsumerDeal>> findCanceledConsumerDeals(
            JwtAuthenticationToken auth) {
        return ResponseEntity.ok(service.findByIsActiveAndConsumerId(
                false,
                auth.getName()
        ));
    }
}
