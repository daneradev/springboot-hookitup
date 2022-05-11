package com.hookitup.Rating.repository.consumer;

import com.hookitup.Rating.model.consumer.PendingRatingC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PendingRatingCRepo extends JpaRepository<PendingRatingC, Long> {

    List<PendingRatingC> findByConsumerId(String id);

    Optional<PendingRatingC> findByIdAndConsumerId(Long id, String consumerId);
}
