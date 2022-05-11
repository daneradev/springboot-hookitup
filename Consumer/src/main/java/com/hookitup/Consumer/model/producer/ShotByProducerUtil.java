package com.hookitup.Consumer.model.producer;

import com.hookitup.Consumer.model.consumer.Offer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShotByProducerUtil {

    private String producerId;
    private LocalDateTime publishDate;
    private String shotId;

    public String turnClassIntoMessageString(String divider) {
        List<String> string = List.of(
                producerId,
                publishDate.toString(),
                shotId);

        return string
                .stream()
                .collect(Collectors.joining(divider));
    }

    public ShotByProducerUtil(Offer offer) {
        producerId = offer.getProducerId();
        publishDate = offer.getShotPublishDate();
        shotId = offer.getShotId();
    }
}
