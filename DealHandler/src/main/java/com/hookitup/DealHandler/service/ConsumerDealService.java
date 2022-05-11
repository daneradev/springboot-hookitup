package com.hookitup.DealHandler.service;

import com.hookitup.DealHandler.model.consumer.ConsumerDeal;
import com.hookitup.DealHandler.repository.ConsumerDealRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerDealService {

    @Autowired
    private ConsumerDealRepo repo;

    public List<ConsumerDeal> findByIsActiveAndConsumerId(boolean isActive, String consumerId) {
        return repo.findByIsActiveAndConsumerId(isActive, consumerId);
    }

    public void insertConsumerDeal(ConsumerDeal consumerDeal) {
        repo.insert(consumerDeal);
    }
}
