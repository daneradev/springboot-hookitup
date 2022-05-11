package com.hookitup.Rating.model.producer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producer {

    @Id
    private String id;

    @Column
    private double rating;

    @Column
    private int numberRatings;

    public void sumNumberRatings() {
        numberRatings++;
    }

    public void sumRating(double newRating) {
        rating += newRating;
    }

}
