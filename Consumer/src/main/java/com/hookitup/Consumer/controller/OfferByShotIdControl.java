package com.hookitup.Consumer.controller;

import com.hookitup.Consumer.model.consumer.OfferByShotId;
import com.hookitup.Consumer.service.OfferByShotIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/offerByShotId")
public class OfferByShotIdControl {

    @Autowired
    private OfferByShotIdService service;

    @PreAuthorize("hasAuthority('SCOPE_PRODUCER')")
    @GetMapping(path = "/{shotId}")
    public ResponseEntity<List<OfferByShotId>> findAllOfferByShotId(
            @PathVariable(name = "shotId") String shotId,
            JwtAuthenticationToken auth
    ) {
        return ResponseEntity.ok(service.findAllByShotId(
                auth.getName(),
                shotId));
    }

}
