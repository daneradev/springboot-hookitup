package com.hookitup.Producer.model.producer;

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

@Table(value = "shot_by_id")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShotById {

    @PrimaryKeyColumn(name = "shot_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String shotId;

    @PrimaryKeyColumn(name = "publish_date", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private LocalDateTime publishDate;

    @Column(value = "producer_id")
    private String producerId;

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

    public ShotById(Shot shot) {
        shotId = shot.getShotId();
        publishDate = shot.getPublishDate();
        producerId = shot.getProducerId();
        producerName = shot.getProducerName();
        title = shot.getTitle();
        description = shot.getDescription();
        product = shot.getProduct();
        amount = shot.getAmount();
        price = shot.getPrice();
    }

    public ShotById(Deal deal) {
        shotId = deal.getShotId();
        publishDate = deal.getShotPublishDate();
    }
}
