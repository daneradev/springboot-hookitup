package com.hookitup.Producer.model.consumer;

import java.time.LocalDateTime;

public record OfferByShotId (
        String producerId,
        String shotId,
        LocalDateTime publishDate,
        String consumerId,
        String offerId,
        double price,
        String consumerName
) {}
