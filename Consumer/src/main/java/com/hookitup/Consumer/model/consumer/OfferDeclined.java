package com.hookitup.Consumer.model.consumer;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OfferDeclined {
    private String consumerId;
    private LocalDateTime offerPublishDate;
    private LocalDateTime shotPublishDate;
    private String offerId;
    private String consumerName;
    private String producerName;
    private String shotId;
    private String product;
    private String producerId;
    private Double price;
    private Double offeredPrice;
    private LocalDateTime decliningDate;

    public OfferDeclined(String message, String divider) {

        String[] dealInfo = message.split(divider);

        consumerId = dealInfo[0];
        offerPublishDate = LocalDateTime.parse(dealInfo[1]);
        shotPublishDate = LocalDateTime.parse(dealInfo[2]);
        offerId = dealInfo[3];
        consumerName = dealInfo[4];
        producerName = dealInfo[5];
        shotId = dealInfo[6];
        product = dealInfo[7];
        producerId = dealInfo[8];
        price = Double.parseDouble(dealInfo[9]);
        offeredPrice = Double.parseDouble(dealInfo[10]);
        decliningDate = LocalDateTime.parse(dealInfo[11]);
    }
}
