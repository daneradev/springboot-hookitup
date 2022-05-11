package com.hookitup.Producer.model.rating;

public record pendingRating(
        String ratingId,
        String customerId,
        String consumerName
) {
}
