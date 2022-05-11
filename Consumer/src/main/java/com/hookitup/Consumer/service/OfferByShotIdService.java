package com.hookitup.Consumer.service;


import com.hookitup.Consumer.model.consumer.OfferByShotId;
import com.hookitup.Consumer.repository.OfferByShotIdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferByShotIdService {

    @Autowired
    private OfferByShotIdRepo offerByShotIdRepo;

    public OfferByShotId insert(OfferByShotId offerByShotId) {
        return offerByShotIdRepo.insert(offerByShotId);
    }

    public List<OfferByShotId> findAllByShotId(String producerId, String shotId) {
        return offerByShotIdRepo.findByProducerIdAndShotId(producerId, shotId);
    }

    public void delete(OfferByShotId offerByShotId) {
        offerByShotIdRepo.delete(offerByShotId);
    }

    public void deleteByListOf(List<OfferByShotId> offersByShot) {
        offerByShotIdRepo.deleteAll(offersByShot);
    }
}
