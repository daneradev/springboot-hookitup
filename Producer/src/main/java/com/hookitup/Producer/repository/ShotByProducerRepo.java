package com.hookitup.Producer.repository;

import com.hookitup.Producer.model.producer.ShotByProducer;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShotByProducerRepo extends CassandraRepository<ShotByProducer, String> {

    List<ShotByProducer> findByProducerId(String id);

    @Query("select * from shot_by_producer where producer_id = ?0 and publish_date = ?1 and shot_id = ?2")
    Optional<ShotByProducer> findShotByProducer(
            String producerId,
            LocalDateTime publishDate,
            String shotId
    );

    /*
    Optional<ShotByProducer> findByProducerIdAndPublishDateAndShotId(
            String producerId,
            LocalDateTime publishDate,
            String shotId
    );
     */

}
