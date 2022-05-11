package com.hookitup.DealHandler.controller;

import com.hookitup.DealHandler.model.producer.ProducerDeal;
import com.hookitup.DealHandler.service.ProducerDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/producerDeal")
public class ProducerDealControl {

    @Autowired
    private ProducerDealService service;

    @PreAuthorize("hasAuthority('SCOPE_PRODUCER')")
    @GetMapping
    public ResponseEntity<List<ProducerDeal>> findProducerDeals(
            JwtAuthenticationToken auth
    ) {
        return ResponseEntity.ok(service.findByIsActiveAndProducerId(
                true,
                auth.getName()));
    }

    @PreAuthorize("hasAuthority('SCOPE_PRODUCER')")
    @GetMapping
    public ResponseEntity<List<ProducerDeal>> findCanceledProducerDeals(
            JwtAuthenticationToken auth
    ) {
        return ResponseEntity.ok(service.findByIsActiveAndProducerId(
                false,
                auth.getName()));
    }
}
