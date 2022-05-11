package com.hookitup.Rating.model.consumer;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class AssessmentForConsumer{

    @NotBlank
    String consumerId;

    @Min(0)
    @Max(value = 5, message = "Maximum 5")
    double rating;

    @Size(max = 250)
    String comment;

    @NotBlank
    long producerPendingId;
}
