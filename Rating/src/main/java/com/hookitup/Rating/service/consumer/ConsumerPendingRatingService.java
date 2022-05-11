package com.hookitup.Rating.service.consumer;

import com.hookitup.Rating.model.consumer.PendingRatingC;
import com.hookitup.Rating.repository.consumer.PendingRatingCRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumerPendingRatingService {

    @Autowired
    private PendingRatingCRepo repo;

    public List<PendingRatingC> findAllByConsumerId(String consumerId) {
        return repo.findByConsumerId(consumerId);
    }

    public Optional<PendingRatingC> findByIdAndConsumer(long id, String consumerId) {
        return repo.findByIdAndConsumerId(id, consumerId);
    }

    public void save(PendingRatingC pendingRatingC) {
        repo.save(pendingRatingC);
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

    public void deleteByEntity(PendingRatingC pendingRatingC) {
        repo.delete(pendingRatingC);
    }
}
