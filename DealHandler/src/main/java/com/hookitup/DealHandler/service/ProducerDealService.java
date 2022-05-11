package com.hookitup.DealHandler.service;

import com.hookitup.DealHandler.model.producer.ProducerDeal;
import com.hookitup.DealHandler.repository.ProducerDealRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerDealService {

    @Autowired
    private ProducerDealRepo repo;

    public List<ProducerDeal> findByIsActiveAndProducerId(boolean isActive, String producerId) {
        return repo.findByIsActiveAndProducerId(isActive, producerId);
    }

    public void insertProducerDeal(ProducerDeal producerDeal) {
        repo.insert(producerDeal);
    }
}
