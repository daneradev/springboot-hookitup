package com.hookitup.Rating.controller.producer;

import com.hookitup.Rating.model.producer.CommentP;
import com.hookitup.Rating.model.producer.Producer;
import com.hookitup.Rating.service.producer.ProducerCommentService;
import com.hookitup.Rating.service.producer.ProducerService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/commentP")
public class ProducerCommentControl {

    @Autowired
    private ProducerCommentService producerCommentService;

    @Autowired
    private ProducerService producerService;

    @CircuitBreaker(name = "ratingCircuit", fallbackMethod = "findCommentsFallback")
    @GetMapping(path = "/{producerId}/all")
    public ResponseEntity<List<CommentP>> findAllByProducerId(
            @PathVariable String producerId) {

        return ResponseEntity.ok(producerCommentService.findAllByProducerId(producerId));
    }

    public ResponseEntity<List<CommentP>> findCommentsFallback(String producerId, Throwable t) {

        Optional<Producer> producer = producerService.findById(producerId);

        if (producer.isEmpty())
            return ResponseEntity.notFound().build();

        double rating = 0;

        if (producer.get().getNumberRatings() > 0)
            rating = producer.get().getRating() / producer.get().getNumberRatings();

        List<CommentP> comments = List.of(
                CommentP.builder()
                        .consumerName("community")
                        .comment("the rating of this person by the community is: " + rating)
                        .build()
        );

        return  ResponseEntity.ok(comments);
    }


}
