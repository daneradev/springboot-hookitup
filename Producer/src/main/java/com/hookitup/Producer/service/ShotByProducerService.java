package com.hookitup.Producer.service;

import com.hookitup.Producer.model.producer.ShotByProducer;
import com.hookitup.Producer.repository.ShotByProducerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShotByProducerService {

    @Autowired
    private ShotByProducerRepo shotRepository;

    public void insertShot(ShotByProducer newShotByProducer) {
        shotRepository.insert(newShotByProducer);
    }

    public List<ShotByProducer> findAllShotByProducer(String producerId) {
        return shotRepository.findByProducerId(producerId);
    }

    public Optional<ShotByProducer> findByShot(
            String producerId,
            LocalDateTime publishDate,
            String shotId
    ) {
        return shotRepository.findShotByProducer(
                producerId, publishDate, shotId
        );
    }

    public void deleteShot(ShotByProducer shot) {
        shotRepository.delete(shot);
    }



}
