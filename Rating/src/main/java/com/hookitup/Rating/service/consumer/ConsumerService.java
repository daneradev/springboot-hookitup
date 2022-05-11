package com.hookitup.Rating.service.consumer;

import com.hookitup.Rating.model.consumer.ConsumerApp;
import com.hookitup.Rating.repository.consumer.ConsumerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepo repo;

    public void save(ConsumerApp consumer) {
        repo.save(consumer);
    }

    public Optional<ConsumerApp> findById(String consumerId) {
        return repo.findById(consumerId);
    }
}
