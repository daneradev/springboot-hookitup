package com.hookitup.Consumer.service;

import com.hookitup.Consumer.model.consumer.OfferByConsumer;
import com.hookitup.Consumer.model.consumer.OfferByShotId;
import com.hookitup.Consumer.model.deal.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeleteAllOffersRelatedToDeal {

    @Autowired
    private OfferByConsumerService offerByConsumerService;

    @Autowired
    private  OfferByShotIdService offerByShotIdService;


    public void delete(Deal deal) {
        List<OfferByShotId> offersByShot = offerByShotIdService.findAllByShotId(
                deal.getProducerId(),
                deal.getShotId()
        );

        List<OfferByConsumer> offersByConsumer = new ArrayList<>();

        for (OfferByShotId offer : offersByShot) {
            offersByConsumer.add(new OfferByConsumer(
                    offer.getConsumerId(),
                    offer.getPublishDate(),
                    offer.getOfferId()
            ));
        }

        offerByConsumerService.deleteByListOf(offersByConsumer);
        offerByShotIdService.deleteByListOf(offersByShot);
    }

}
