package com.hookitup.Producer.model.consumer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    public String turnClassIntoMessageString(String divider) {
        List<String> string = List.of(
                consumerId,
                offerPublishDate.toString(),
                shotPublishDate.toString(),
                offerId,
                consumerName,
                producerName,
                shotId,
                product,
                producerId,
                price.toString(),
                offeredPrice.toString(),
                decliningDate.toString());

        return string
                .stream()
                .collect(Collectors.joining(divider));
    }

    public void setDecliningDate() {
        decliningDate = LocalDateTime.now();
    }
}
