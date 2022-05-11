package com.hookitup.Consumer.service.client;

import com.hookitup.Consumer.model.producer.ShotById;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "producer", url = "http://localhost:1012/producer" )
public interface ProducerFeignClient {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/shotById",
            consumes = "application/json")
    List<ShotById> findAllShotById();

}
