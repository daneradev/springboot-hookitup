package com.hookitup.Rating.controller.producer;

import com.hookitup.Rating.model.consumer.AssessmentForConsumer;
import com.hookitup.Rating.model.consumer.CommentC;
import com.hookitup.Rating.model.consumer.ConsumerApp;
import com.hookitup.Rating.model.producer.PendingRatingP;
import com.hookitup.Rating.service.consumer.ConsumerCommentService;
import com.hookitup.Rating.service.consumer.ConsumerService;
import com.hookitup.Rating.service.producer.ProducerPendingRatingService;
import com.hookitup.Rating.service.producer.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/producer")
public class ProducerControl {

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private ProducerPendingRatingService producerPendingRatingService;

    @Autowired
    private ConsumerCommentService consumerCommentService;

    @Autowired
    private ProducerService producerService;

    @PreAuthorize("hasAuthority('SCOPE_PRODUCER')")
    @PostMapping(path = "/assessment")
    public ResponseEntity<String> producerAssessesToConsumer(
            @RequestBody AssessmentForConsumer assessment,
            JwtAuthenticationToken auth) {

        Optional<ConsumerApp> consumer = consumerService
                .findById(assessment.getConsumerId());

        if (consumer.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("consumer not found");

        Optional<PendingRatingP> pendingRatingP = producerPendingRatingService
                .findByIdAndProducer(assessment.getProducerPendingId(), auth.getName());

        if (pendingRatingP.isEmpty())
            return ResponseEntity.notFound().build();

        consumer.get().sumNumberRatings();
        consumer.get().sumRating(assessment.getRating());
        consumerService.save(consumer.get());

        if (assessment.getComment() != null) {
            CommentC commentC = CommentC.builder()
                    .producerName(auth.getToken().getClaimAsString("email"))
                    .producerId(auth.getName())
                    .comment(assessment.getComment())
                    .consumer(consumer.get())
                    .build();
        }

        producerPendingRatingService.deleteByEntity(pendingRatingP.get());

        return ResponseEntity.ok("done");
    }

}
