package com.hookitup.Rating.repository.producer;

import com.hookitup.Rating.model.producer.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepo extends JpaRepository<Producer, String> {
}
