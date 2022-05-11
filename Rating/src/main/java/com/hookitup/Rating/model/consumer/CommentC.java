package com.hookitup.Rating.model.consumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "consumer_comment")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentC {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String producerId;

    @Column
    private String producerName;

    @Column
    private String comment;

    @ManyToOne
    private ConsumerApp consumer;
}
