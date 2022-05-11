package com.hookitup.Consumer.service;

import com.hookitup.Consumer.model.consumer.OfferDeclinedEntity;
import com.hookitup.Consumer.repository.OfferDeclinedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferDeclinedService {

    @Autowired
    private OfferDeclinedRepo repository;

    public List<OfferDeclinedEntity> findOffersDeclined(String consumerId) {
        return repository.findByConsumerId(consumerId);
    }

    public void insertOfferDeclined(OfferDeclinedEntity offerDeclined) {
        repository.save(offerDeclined);
    }
}
