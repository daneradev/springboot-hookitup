package com.hookitup.Producer.model.rating;

import java.time.LocalDateTime;

public record Comment(
        String publisher,
        String comment,
        LocalDateTime publishDate
) {
}
