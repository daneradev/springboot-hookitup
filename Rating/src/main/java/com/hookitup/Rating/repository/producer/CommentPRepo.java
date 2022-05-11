package com.hookitup.Rating.repository.producer;

import com.hookitup.Rating.model.producer.CommentP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentPRepo extends JpaRepository<CommentP, Long> {
    List<CommentP> findByProducerId(String id);

    Optional<CommentP> findByIdAndProducerId(long id, String producerId);
}
