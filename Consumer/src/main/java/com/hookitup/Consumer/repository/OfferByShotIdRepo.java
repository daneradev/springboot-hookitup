package com.hookitup.Consumer.repository;

import com.hookitup.Consumer.model.consumer.OfferByShotId;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferByShotIdRepo extends CassandraRepository<OfferByShotId, String> {
    List<OfferByShotId> findByProducerIdAndShotId(String producerId, String shotId);
}
