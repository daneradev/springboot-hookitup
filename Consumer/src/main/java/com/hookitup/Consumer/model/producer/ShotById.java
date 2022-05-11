package com.hookitup.Consumer.model.producer;


import java.time.LocalDateTime;

public record ShotById(
        String shotId,
        LocalDateTime publishDate,
        String producerId,
        String producerName,
        String title,
        String description,
        String product,
        double amount,
        double price) { }


