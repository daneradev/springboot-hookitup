package com.hookitup.Rating.service.producer;

import com.hookitup.Rating.model.producer.CommentP;
import com.hookitup.Rating.repository.producer.CommentPRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerCommentService {

    @Autowired
    private CommentPRepo repo;


    public List<CommentP> findAllByProducerId(String producerId) {
        return repo.findByProducerId(producerId);
    }

    public void save(CommentP commentP) {
        repo.save(commentP);
    }

    public boolean delete(long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
