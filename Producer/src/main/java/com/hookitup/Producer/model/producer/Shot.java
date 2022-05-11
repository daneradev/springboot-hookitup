package com.hookitup.Producer.model.producer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shot {

    @NotBlank
    private String shotId;

    private LocalDateTime publishDate;

    private String producerId;

    private String producerName;

    @Size(min = 5, max = 50, message = "Minimum 5 characters, Maximum 50")
    private String title;

    @Size(min = 5, max = 200, message = "Minimum 5 characters, Maximum 150")
    private String description;

    @Size(min = 5, max = 50, message = "Minimum 5 characters, Maximum 15")
    private String product;

    @Min(value = 1, message = "amount has to be at least 1")
    private double amount;

    @Min(value = 1, message = "price has to be at least 1")
    private double price;

}
