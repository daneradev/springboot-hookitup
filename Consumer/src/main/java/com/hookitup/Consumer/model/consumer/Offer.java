package com.hookitup.Consumer.model.consumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Offer {

    private String consumerId;

    private LocalDateTime offerPublishDate;

    private LocalDateTime shotPublishDate;

    @NotBlank
    private String offerId;

    private String consumerName;

    @NotBlank
    private String shotId;

    @NotBlank
    private String product;

    @NotBlank
    private String producerId;

    @NotBlank
    private double price;

    public void setOfferPublishDate() {
        offerPublishDate = LocalDateTime.now();
    }

}
