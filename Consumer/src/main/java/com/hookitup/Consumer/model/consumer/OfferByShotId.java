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

@Table(value = "offer_by_shotId")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferByShotId {

    @PrimaryKeyColumn(name = "producer_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String producerId;

    @PrimaryKeyColumn(name = "shot_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private String shotId;

    @PrimaryKeyColumn(name = "publish_date", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private LocalDateTime publishDate;

    @PrimaryKeyColumn(name = "consumer_id", ordinal = 3, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private String consumerId;

    @Column(value = "consumer_name")
    private String consumerName;

    @Column(value = "offer_id")
    private String offerId;

    @Column
    private double price;

    public OfferByShotId(Offer offer) {
        producerId = offer.getProducerId();
        shotId = offer.getShotId();
        publishDate = offer.getOfferPublishDate();
        consumerId = offer.getConsumerId();
        consumerName = offer.getConsumerName();
        offerId = offer.getOfferId();
        price = offer.getPrice();
    }

    public OfferByShotId(Deal deal) {
        producerId = deal.getProducerId();
        shotId = deal.getShotId();
        publishDate = deal.getOfferPublishDate();
        consumerId = deal.getConsumerId();
    }

    public OfferByShotId(OfferDeclined offerDeclined) {
        producerId = offerDeclined.getProducerId();
        shotId = offerDeclined.getShotId();
        publishDate = offerDeclined.getOfferPublishDate();
        consumerId = offerDeclined.getConsumerId();
    }
}
