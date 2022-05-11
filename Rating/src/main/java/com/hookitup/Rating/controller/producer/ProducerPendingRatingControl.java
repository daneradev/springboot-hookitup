package com.hookitup.Rating.controller.producer;

import com.hookitup.Rating.model.producer.PendingRatingP;
import com.hookitup.Rating.service.producer.ProducerPendingRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/pendingP")
public class ProducerPendingRatingControl {

    @Autowired
    private ProducerPendingRatingService service;

    @GetMapping(path = "/all")
    public ResponseEntity<List<PendingRatingP>> findAll(JwtAuthenticationToken auth) {
        return ResponseEntity.ok(
                service.findAllByProducerId(auth.getName())
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteById(
            @PathVariable long id,
            JwtAuthenticationToken auth) {

        Optional<PendingRatingP> pendingRatingP = service
                .findByIdAndProducer(id, auth.getName());

        if (pendingRatingP.isEmpty())
            return ResponseEntity.notFound().build();

        service.deleteByEntity(pendingRatingP.get());

        return ResponseEntity.ok("deleted");
    }
}
