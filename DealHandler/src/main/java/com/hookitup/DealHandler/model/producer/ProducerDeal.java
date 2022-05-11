package com.hookitup.DealHandler.model.producer;

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

@Table(value = "producer_deal")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProducerDeal {

    @PrimaryKeyColumn(name = "is_active", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private boolean isActive;

    @PrimaryKeyColumn(name = "producer_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String producerId;

    @PrimaryKeyColumn(name = "deal_date", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private LocalDateTime dealDate;

    @Column(value = "consumer_id")
    private String consumerId;

    @Column(value = "consumer_name")
    private String consumerName;

    @Column
    private String product;

    @Column
    private double amount;

    @Column
    private double price;

    public ProducerDeal(Deal deal) {
        isActive = deal.getIsActive();
        producerId = deal.getProducerId();
        dealDate = LocalDateTime.now();
        consumerId = deal.getConsumerId();
        consumerName = deal.getConsumerName();
        product = deal.getProduct();
        amount = deal.getAmount();
        price = deal.getPrice();
    }

}
