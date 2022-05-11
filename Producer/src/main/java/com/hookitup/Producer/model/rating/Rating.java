package com.hookitup.Producer.model.rating;

public record Rating(
        String ratingId,
        String from,
        String to,
        float rating
) {
}
