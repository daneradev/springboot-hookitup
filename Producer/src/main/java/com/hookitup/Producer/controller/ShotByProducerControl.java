package com.hookitup.Producer.controller;

import com.hookitup.Producer.model.producer.ShotByProducer;
import com.hookitup.Producer.service.ShotByProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/shotByProducer")
public class ShotByProducerControl {

    @Autowired
    private ShotByProducerService shotService;

    @PreAuthorize("hasAuthority('SCOPE_PRODUCER')")
    @GetMapping
    public ResponseEntity<List<ShotByProducer>> findAllShotById(JwtAuthenticationToken auth) {
        return  ResponseEntity.ok(shotService.findAllShotByProducer(
                auth.getName()
        ));
    }


}
