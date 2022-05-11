package com.hookitup.DealHandler.repository;

import com.hookitup.DealHandler.model.consumer.ConsumerDeal;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumerDealRepo extends CassandraRepository<ConsumerDeal, String>{
    List<ConsumerDeal> findByIsActiveAndConsumerId(boolean isActive, String consumerId);
}
