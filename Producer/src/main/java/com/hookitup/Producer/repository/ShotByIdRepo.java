package com.hookitup.Producer.repository;

import com.hookitup.Producer.model.producer.ShotById;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShotByIdRepo extends CassandraRepository<ShotById, String> {

    List<ShotById> findByShotId(String shotId);
}
