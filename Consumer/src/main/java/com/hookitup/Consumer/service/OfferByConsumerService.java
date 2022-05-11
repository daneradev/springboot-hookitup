package com.hookitup.Consumer.service;

import com.hookitup.Consumer.model.consumer.OfferByConsumer;
import com.hookitup.Consumer.repository.OfferByConsumerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OfferByConsumerService {

    @Autowired
    private OfferByConsumerRepo offerByConsumerRepo;

    public OfferByConsumer insert(OfferByConsumer offerByConsumer) {
        return offerByConsumerRepo.insert(offerByConsumer);
    }

    public List<OfferByConsumer> findAllByConsumer(String consumerId) {
        return offerByConsumerRepo.findByConsumerId(consumerId);
    }

    public Optional<OfferByConsumer> findByConsumer(
            String consumerId,
            LocalDateTime publishDate,
            String offerId) {

        return offerByConsumerRepo.findOfferByConsumer(
                consumerId, publishDate, offerId);
    }

    public void delete(OfferByConsumer offerByConsumer) {
        offerByConsumerRepo.delete(offerByConsumer);
    }

    public void deleteByListOf(List<OfferByConsumer> offersByConsumer) {
        offerByConsumerRepo.deleteAll(offersByConsumer);
    }
}
