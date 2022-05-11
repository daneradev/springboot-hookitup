package com.hookitup.Rating.controller.consumer;

import com.hookitup.Rating.model.consumer.PendingRatingC;
import com.hookitup.Rating.model.producer.AssessmentForProducer;
import com.hookitup.Rating.model.producer.CommentP;
import com.hookitup.Rating.model.producer.Producer;
import com.hookitup.Rating.service.consumer.ConsumerPendingRatingService;
import com.hookitup.Rating.service.producer.ProducerCommentService;
import com.hookitup.Rating.service.producer.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/consumer")
public class ConsumerControl {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ConsumerPendingRatingService consumerPendingRatingService;

    @Autowired
    private ProducerCommentService producerCommentService;

    @PreAuthorize("hasAuthority('SCOPE_CONSUMER')")
    @PostMapping(path = "/assessment")
    public ResponseEntity<String> consumerAssessToProducer(
            @RequestBody AssessmentForProducer assessment,
            JwtAuthenticationToken auth
            ) {


        Optional<Producer> producer = producerService
                .findById(assessment.getProducerId());

        if (producer.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("producer not found");

        Optional<PendingRatingC> pendingRatingC = consumerPendingRatingService
                .findByIdAndConsumer(assessment.getConsumerPendingId(), auth.getName());

        if (pendingRatingC.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("consumer pending not found");

        producer.get().sumNumberRatings();
        producer.get().sumRating(assessment.getRating());
        producerService.save(producer.get());

        if (assessment.getComment() != null) {
            CommentP commentP = CommentP.builder()
                    .consumerId(auth.getName())
                    .consumerName(auth.getToken().getClaimAsString("email"))
                    .comment(assessment.getComment())
                    .producer(producer.get())
                    .build();
        }

        consumerPendingRatingService.deleteByEntity(pendingRatingC.get());
        return ResponseEntity.ok("done");
    }

}
