package com.hookitup.Producer.service;

import com.hookitup.Producer.model.producer.ShotById;
import com.hookitup.Producer.repository.ShotByIdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShotByIdService {

    @Autowired
    private ShotByIdRepo shotByIdRepo;


    public void insertShotById(ShotById newShotById) {
        shotByIdRepo.insert(newShotById);
    }

    public List<ShotById> findPage(int pageSize) {
        Slice<ShotById> page = shotByIdRepo.findAll(CassandraPageRequest.first(pageSize));
        return page.getContent();
    }

    public List<ShotById> findAllShotById() {
        return shotByIdRepo.findAll();
    }


    public void deleteShotById(ShotById shotById) {
        shotByIdRepo.delete(shotById);
    }

}
