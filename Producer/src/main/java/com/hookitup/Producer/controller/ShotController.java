package com.hookitup.Producer.controller;

import com.hookitup.Producer.model.producer.Shot;
import com.hookitup.Producer.model.producer.ShotById;
import com.hookitup.Producer.model.producer.ShotByProducer;
import com.hookitup.Producer.service.ShotByIdService;
import com.hookitup.Producer.service.ShotByProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping(path = "/shot")
@Slf4j
public class ShotController {

    @Autowired
    private ShotByIdService shotByIdService;

    @Autowired
    private ShotByProducerService shotByProducerService;

    @PreAuthorize("hasAuthority('SCOPE_PRODUCER')")
    @PostMapping(path = "/insert")
    public ResponseEntity<String> insertShot(
            @Valid @RequestBody Shot shot,
            JwtAuthenticationToken auth) {

        shot.setProducerId(auth.getName());
        shot.setProducerName(auth.getToken().getClaimAsString("email"));
        shot.setShotId(UUID.randomUUID().toString());
        shot.setPublishDate(LocalDateTime.now());

        shotByProducerService.insertShot(new ShotByProducer(shot));
        shotByIdService.insertShotById(new ShotById(shot));
        return ResponseEntity.ok("ok");
    }

    @PreAuthorize("hasAuthority('SCOPE_PRODUCER')")
    @DeleteMapping
    public ResponseEntity<String> deleteShot(@Valid @RequestBody Shot shot,
                                             JwtAuthenticationToken auth) {

        shot.setProducerId(auth.getName());
        shotByProducerService.deleteShot(new ShotByProducer(shot));
        shotByIdService.deleteShotById(new ShotById(shot));

        return ResponseEntity.ok("deleted");
    }

}
