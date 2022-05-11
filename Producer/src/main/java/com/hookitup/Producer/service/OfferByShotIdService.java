package com.hookitup.Producer.service;

import com.hookitup.Producer.model.consumer.OfferByShotId;
import com.hookitup.Producer.service.client.ConsumerFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OfferByShotIdService {

    @Autowired
    ConsumerFeignClient consumerFeignClient;

    public List<OfferByShotId> findAllOffersByShotId(String producerId, String shotId) {
        return consumerFeignClient.getOfferByShotId(producerId, shotId);
    }
}
