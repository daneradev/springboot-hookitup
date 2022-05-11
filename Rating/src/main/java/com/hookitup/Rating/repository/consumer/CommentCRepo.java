package com.hookitup.Rating.repository.consumer;

import com.hookitup.Rating.model.consumer.CommentC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentCRepo extends JpaRepository<CommentC, Long> {
    List<CommentC> findByConsumerId(String id);

    Optional<CommentC> findByIdAndConsumerId(long id, String producerId);
}
