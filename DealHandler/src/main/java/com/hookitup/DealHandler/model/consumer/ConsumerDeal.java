package com.hookitup.DealHandler.model.consumer;

import com.hookitup.DealHandler.model.deal.Deal;
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

@Table(value = "consumer_deal")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsumerDeal {

    @PrimaryKeyColumn(name = "is_active", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private boolean isActive;

    @PrimaryKeyColumn(name = "consumer_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String consumerId;

    @PrimaryKeyColumn(name = "deal_date", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private LocalDateTime dealDate;

    @Column(value = "producer_id")
    private String producerId;

    @Column(value = "producer_name")
    private String producerName;

    @Column
    private String product;

    @Column
    private double amount;

    @Column
    private double price;

    public ConsumerDeal(Deal deal) {
        isActive = deal.getIsActive();
        consumerId = deal.getConsumerId();
        dealDate = LocalDateTime.now();
        producerId = deal.getProducerId();
        producerName = deal.getProducerName();
        product = deal.getProduct();
        amount = deal.getAmount();
        price = deal.getPrice();
    }
}
