package com.hookitup.Producer.service.client;

import com.hookitup.Producer.model.consumer.OfferByShotId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "consumer",url = "http://localhost:1012/consumer")
public interface ConsumerFeignClient {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/offerByShotId/{producerId}/{shotId}",
            consumes = "application/json")
    List<OfferByShotId> getOfferByShotId(
            @PathVariable(name = "producerId") String producerId,
            @PathVariable(name = "shotId") String shotId
    );
}
