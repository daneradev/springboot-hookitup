package com.hookitup.Producer.model.producer;


import com.hookitup.Producer.model.consumer.OfferDeclined;
import com.hookitup.Producer.model.deal.Deal;
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

@Table(value = "shot_by_producer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShotByProducer {

    @PrimaryKeyColumn(name = "producer_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String producerId;

    @PrimaryKeyColumn(name = "publish_date", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private LocalDateTime publishDate;

    @PrimaryKeyColumn(name = "shot_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private String shotId;

    @Column(value = "producer_name")
    private String producerName;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String product;

    @Column
    private double amount;

    @Column
    private double price;

    @Column
    private int numberOffers;

    public void sumNumberOffers() {
        numberOffers++;
    }

    public void subtractNumberOffers() {
        if (numberOffers > 0)
            numberOffers--;
    }

    public ShotByProducer(Shot shot) {
        producerId = shot.getProducerId();
        publishDate = shot.getPublishDate();
        shotId = shot.getShotId();
        producerName = shot.getProducerName();
        title = shot.getTitle();
        description = shot.getDescription();
        product = shot.getProduct();
        amount = shot.getAmount();
        price = shot.getPrice();
    }

    public ShotByProducer(Deal deal) {
        producerId = deal.getProducerId();
        publishDate = deal.getShotPublishDate();
        shotId = deal.getShotId();
    }

    public ShotByProducer(OfferDeclined offerDeclined) {
        producerId = offerDeclined.getProducerId();
        publishDate = offerDeclined.getShotPublishDate();
        shotId = offerDeclined.getShotId();
    }
}
