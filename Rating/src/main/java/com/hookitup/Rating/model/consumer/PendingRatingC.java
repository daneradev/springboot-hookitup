package com.hookitup.Rating.model.consumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "consumer_pending")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PendingRatingC {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String producerId;

    @Column
    private String producerName;

    @ManyToOne
    private ConsumerApp consumer;


}