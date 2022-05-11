package com.hookitup.Producer.model.rating;

import java.util.List;

public record CustomerRating(
        String customerId,
        float rating,
        int ratingNumber,
        List<Comment> comments
) {
}
