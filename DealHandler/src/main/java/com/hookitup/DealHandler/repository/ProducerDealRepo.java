package com.hookitup.DealHandler.repository;

import com.hookitup.DealHandler.model.producer.ProducerDeal;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProducerDealRepo extends CassandraRepository<ProducerDeal, String> {

    List<ProducerDeal> findByIsActiveAndProducerId(boolean isActive, String producerId);
}
