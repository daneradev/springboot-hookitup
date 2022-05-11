package com.hookitup.Rating.service.producer;

import com.hookitup.Rating.model.producer.PendingRatingP;
import com.hookitup.Rating.repository.producer.PendingRatingPRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProducerPendingRatingService {

    @Autowired
    private PendingRatingPRepo repo;

    public List<PendingRatingP> findAllByProducerId(String producerId) {
        return repo.findByProducerId(producerId);
    }

    public Optional<PendingRatingP> findByIdAndProducer(
            long id,
            String producerId
    ) {
        return repo.findByIdAndProducerId(id, producerId);
    }

    public void save(PendingRatingP pendingRatingP) {
        repo.save(pendingRatingP);
    }

    public boolean deleteById(long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public void delete(long id) {
        if (repo.existsById(id))
            repo.deleteById(id);

    }

    public void deleteByEntity(PendingRatingP pendingRatingP) {
        repo.delete(pendingRatingP);
    }
}
