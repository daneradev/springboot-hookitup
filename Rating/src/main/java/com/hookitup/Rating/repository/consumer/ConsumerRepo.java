package com.hookitup.Rating.repository.consumer;

import com.hookitup.Rating.model.consumer.ConsumerApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepo extends JpaRepository<ConsumerApp, String> {
}
