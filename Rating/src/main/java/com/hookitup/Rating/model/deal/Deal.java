package com.hookitup.Rating.model.deal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Deal {
    private String producerId;
    private String consumerId;
    private LocalDateTime shotPublishDate;
    private LocalDateTime offerPublishDate;
    private String consumerName;
    private String producerName;
    private String product;
    private String shotId;
    private String offerId;
    private Double amount;
    private Double price;
    private Boolean isActive;

    public Deal(String message, String divider) {
        String[] dealInfo = message.split(divider);

        producerId = dealInfo[0];
        consumerId = dealInfo[1];
        shotPublishDate = LocalDateTime.parse(dealInfo[2]);
        offerPublishDate = LocalDateTime.parse(dealInfo[3]);
        consumerName = dealInfo[4];
        producerName = dealInfo[5];
        product = dealInfo[6];
        shotId = dealInfo[7];
        offerId = dealInfo[8];
        amount = Double.parseDouble(dealInfo[9]);
        price = Double.parseDouble(dealInfo[10]);
        isActive = Boolean.parseBoolean(dealInfo[11]);
    }

    public String turnClassIntoMessageString(String divider) {
        List<String> string = List.of(
                producerId,
                consumerId,
                shotPublishDate.toString(),
                offerPublishDate.toString(),
                consumerName,
                producerName,
                product,
                shotId,
                offerId,
                amount.toString(),
                price.toString(),
                isActive.toString());

        return string
                .stream()
                .collect(Collectors.joining(divider));
    }

}
