package com.hookitup.Rating.service.consumer;


import com.hookitup.Rating.model.consumer.CommentC;
import com.hookitup.Rating.repository.consumer.CommentCRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerCommentService {

    @Autowired
    private CommentCRepo repo;

    public List<CommentC> findAllByConsumerId(String consumerId) {
        return repo.findByConsumerId(consumerId);
    }

    public void save(CommentC commentC) {
        repo.save(commentC);
    }

    public boolean delete(long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
