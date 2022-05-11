package com.hookitup.Consumer.model.consumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Table(value = "offer_turned_down")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferDeclinedEntity {
    @PrimaryKeyColumn(name = "consumer_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String consumerId;

    @PrimaryKeyColumn(name = "declining_date", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private LocalDateTime decliningDate;

    @PrimaryKeyColumn(name = "offer_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private String offerId;

    @Column(value = "producerName")
    private String producerName;

    @Column
    private String product;

    @Column
    private double price;

    @Column(value = "offered_price")
    private double offeredPrice;

    public OfferDeclinedEntity(OfferDeclined offerDeclined) {
        consumerId = offerDeclined.getConsumerId();
        decliningDate = offerDeclined.getDecliningDate();
        offerId = offerDeclined.getOfferId();
        producerName = offerDeclined.getProducerName();
        product = offerDeclined.getProduct();
        price = offerDeclined.getPrice();
        offeredPrice = offerDeclined.getOfferedPrice();
    }
}
