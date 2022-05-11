package com.hookitup.Rating.controller.consumer;

import com.hookitup.Rating.model.consumer.CommentC;
import com.hookitup.Rating.model.consumer.ConsumerApp;
import com.hookitup.Rating.service.consumer.ConsumerCommentService;
import com.hookitup.Rating.service.consumer.ConsumerService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/commentC")
public class ConsumerCommentControl {

    @Autowired
    private ConsumerCommentService consumerCommentService;

    @Autowired
    private ConsumerService consumerService;

    @Bulkhead(name = "ratingBulkheadPool", type = Bulkhead.Type.SEMAPHORE, fallbackMethod = "findCommentsFallback")
    @GetMapping(path = "/{consumerId}/all")
    public ResponseEntity<List<CommentC>> findAllCommentByConsumer(
            @PathVariable String consumerId
    ) {
        return ResponseEntity.ok(consumerCommentService.findAllByConsumerId(
                consumerId
        ));
    }

    public ResponseEntity<List<CommentC>> findCommentsFallback(String consumerId, Throwable t) {

        Optional<ConsumerApp> consumerApp = consumerService.findById(consumerId);

        if (consumerApp.isEmpty())
            return ResponseEntity.notFound().build();

        double rating = 0;

        if (consumerApp.get().getNumberRatings() > 0)
            rating = consumerApp.get().getRating() / consumerApp.get().getNumberRatings();

        List<CommentC> comments = List.of(
                CommentC.builder()
                        .producerName("community")
                        .comment("the rating of this person by the community is: " + rating)
                        .build()
        );

        return  ResponseEntity.ok(comments);
    }
}
