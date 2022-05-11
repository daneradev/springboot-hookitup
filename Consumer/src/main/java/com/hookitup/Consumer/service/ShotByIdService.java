package com.hookitup.Consumer.service;

import com.hookitup.Consumer.model.producer.ShotById;
import com.hookitup.Consumer.service.client.ProducerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShotByIdService {

    @Autowired
    private ProducerFeignClient producerFeignClient;

    public List<ShotById> findAllShotById() {
        return producerFeignClient.findAllShotById();
    }
}
