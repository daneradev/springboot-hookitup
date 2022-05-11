package com.hookitup.Consumer.controller;

import com.hookitup.Consumer.model.consumer.OfferDeclinedEntity;
import com.hookitup.Consumer.service.OfferDeclinedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/offerDeclined")
public class OfferDeclinedControl {

    @Autowired
    private OfferDeclinedService offerDeclinedService;

    @PreAuthorize("hasAuthority('SCOPE_CONSUMER')")
    @GetMapping
    public ResponseEntity<List<OfferDeclinedEntity>> findOffersDeclined(
            JwtAuthenticationToken auth) {

        return ResponseEntity.ok(
                offerDeclinedService.findOffersDeclined(auth.getName())
        );
    }
}
