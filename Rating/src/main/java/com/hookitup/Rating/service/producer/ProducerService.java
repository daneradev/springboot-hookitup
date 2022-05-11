package com.hookitup.Rating.service.producer;

import com.hookitup.Rating.model.producer.Producer;
import com.hookitup.Rating.repository.producer.ProducerRepo;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProducerService {

    @Autowired
    private ProducerRepo repo;

    public void save(Producer producer) {
        repo.save(producer);
    }

    public Optional<Producer> findById(String producerId) {
        return repo.findById(producerId);
    }
}
