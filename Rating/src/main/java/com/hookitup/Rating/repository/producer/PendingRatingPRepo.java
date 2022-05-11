package com.hookitup.Rating.repository.producer;

import com.hookitup.Rating.model.producer.PendingRatingP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PendingRatingPRepo extends JpaRepository<PendingRatingP, Long> {

    List<PendingRatingP> findByProducerId(String id);

    Optional<PendingRatingP> findByIdAndProducerId(Long id, String producerId);
}
