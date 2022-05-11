package com.hookitup.Rating.model.producer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "producer_comment")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentP {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String consumerId;

    @Column
    private String consumerName;

    @Column
    private String comment;

    @ManyToOne
    private Producer producer;
}
