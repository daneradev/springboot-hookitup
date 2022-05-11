package com.hookitup.Producer.model.producer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShotByProducerUtil {

    private String producerId;
    private LocalDateTime publishDate;
    private String shotId;

    public ShotByProducerUtil(String message, String divider) {
        String[] classInfo = message.split(divider);
        producerId = classInfo[0];
        publishDate = LocalDateTime.parse(classInfo[1]);
        shotId = classInfo[2];
    }
}
