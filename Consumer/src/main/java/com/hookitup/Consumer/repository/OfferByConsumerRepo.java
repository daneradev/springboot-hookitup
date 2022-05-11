package com.hookitup.Consumer.repository;

import com.hookitup.Consumer.model.consumer.OfferByConsumer;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OfferByConsumerRepo extends CassandraRepository<OfferByConsumer, String> {

    List<OfferByConsumer> findByConsumerId(String consumerId);

    @Query("select * from offer_by_consumer where consumer_id = ?0 and publish_date = ?1 and offer_id = ?2")
    Optional<OfferByConsumer> findOfferByConsumer(
            String consumerId,
            LocalDateTime publishDate,
            String offerId
    );
}
