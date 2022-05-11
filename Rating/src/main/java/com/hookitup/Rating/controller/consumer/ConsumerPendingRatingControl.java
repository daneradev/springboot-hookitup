package com.hookitup.Rating.controller.consumer;

import com.hookitup.Rating.model.consumer.PendingRatingC;
import com.hookitup.Rating.service.consumer.ConsumerPendingRatingService;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/pendingC")
public class ConsumerPendingRatingControl {

    @Autowired
    private ConsumerPendingRatingService service;

    @PreAuthorize("hasAuthority('SCOPE_CONSUMER')")
    @GetMapping(path = "/all")
    private ResponseEntity<List<PendingRatingC>> findAllByConsumer(
            JwtAuthenticationToken auth
    ) {
        return ResponseEntity.ok(service.findAllByConsumerId(
                auth.getName()
        ));
    }

    @Retry(name = "ratingRetry")
    @PreAuthorize("hasAuthority('SCOPE_CONSUMER')")
    @DeleteMapping(path = "/{id}")
    private ResponseEntity<String> deletePending(
            @PathVariable long id,
            JwtAuthenticationToken auth
    ) {
        Optional<PendingRatingC> pendingRatingC = service
                .findByIdAndConsumer(id, auth.getName());

        if (pendingRatingC.isEmpty())
            return ResponseEntity.notFound().build();

        service.deleteByEntity(pendingRatingC.get());

        return ResponseEntity.noContent().build();
    }
}
