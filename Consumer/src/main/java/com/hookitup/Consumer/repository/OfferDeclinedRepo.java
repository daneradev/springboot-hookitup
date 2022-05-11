package com.hookitup.Consumer.repository;

import com.hookitup.Consumer.model.consumer.OfferDeclinedEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferDeclinedRepo extends CassandraRepository<OfferDeclinedEntity, String> {
    List<OfferDeclinedEntity> findByConsumerId(String consumerId);
}
