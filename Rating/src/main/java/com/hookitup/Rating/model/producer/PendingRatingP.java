package com.hookitup.Rating.model.producer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "producer_pending")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PendingRatingP {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String consumerId;

    @Column
    private String consumerName;

    @ManyToOne
    private Producer producer;
}
