package com.hookitup.Consumer.model.consumer;

import com.hookitup.Consumer.model.deal.Deal;
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

@Table(value = "offer_by_consumer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferByConsumer {
    @PrimaryKeyColumn(name = "consumer_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String consumerId;

    @PrimaryKeyColumn(name = "publish_date", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private LocalDateTime publishDate;

    @PrimaryKeyColumn(name = "offer_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private String offerId;

    @Column(value = "consumer_name")
    private String consumerName;

    @Column(value = "producer_id")
    private String producerId;

    @Column(value = "shot_id")
    private String shotId;

    @Column
    private String product;

    @Column
    private double price;

    public OfferByConsumer(Offer offer) {
        consumerId = offer.getConsumerId();
        publishDate = offer.getOfferPublishDate();
        offerId = offer.getOfferId();
        consumerName = offer.getConsumerName();
        shotId = offer.getShotId();
        product = offer.getProduct();
        price = offer.getPrice();
        producerId = offer.getProducerId();
    }

    public OfferByConsumer(Deal deal) {
        consumerId = deal.getConsumerId();
        publishDate = deal.getOfferPublishDate();
        offerId = deal.getOfferId();
    }

    public OfferByConsumer(String consumerId, LocalDateTime publishDate, String offerId) {
        this.consumerId = consumerId;
        this.publishDate = publishDate;
        this.offerId = offerId;
    }

    public OfferByConsumer(OfferDeclined offerDeclined) {
        consumerId = offerDeclined.getConsumerId();
        publishDate = offerDeclined.getOfferPublishDate();
        offerId = offerDeclined.getOfferId();
    }
}
