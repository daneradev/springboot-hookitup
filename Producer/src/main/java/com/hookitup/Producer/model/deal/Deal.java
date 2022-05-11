package com.hookitup.Producer.model.deal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Deal {

     private String producerId;

     @NotBlank(message = "consumer name can not be empty")
     private String consumerId;

     private LocalDateTime shotPublishDate;

     private LocalDateTime offerPublishDate;

     @Size(min = 5, max = 50, message = "Minimum 5 characters, Maximum 15")
     private String consumerName;

     private String producerName;


     @Size(min = 5, max = 50, message = "Minimum 5 characters, Maximum 15")
     private String product;

     @NotBlank
     private String shotId;

     @NotBlank
     private String offerId;

     @Min(value = 1, message = "amount has to be at least 1")
     private Double amount;

     @Min(value = 1, message = "price has to be at least 1")
     private Double price;

     private Boolean isActive;

     public Deal(String message, String divider) {
          String[] dealInfo = message.split(divider);

          producerId = dealInfo[0];
          consumerId = dealInfo[1];
          shotPublishDate = LocalDateTime.parse(dealInfo[2]);
          offerPublishDate = LocalDateTime.parse(dealInfo[3]);
          consumerName = dealInfo[4];
          producerName = dealInfo[5];
          product = dealInfo[6];
          shotId = dealInfo[7];
          offerId = dealInfo[8];
          amount = Double.parseDouble(dealInfo[9]);
          price = Double.parseDouble(dealInfo[10]);
          isActive = Boolean.parseBoolean(dealInfo[11]);
     }

     public String turnClassIntoMessageString(String divider) {
          List<String> string = List.of(
                  producerId,
                  consumerId,
                  shotPublishDate.toString(),
                  offerPublishDate.toString(),
                  consumerName,
                  producerName,
                  product,
                  shotId,
                  offerId,
                  amount.toString(),
                  price.toString(),
                  isActive.toString());

          return string
                  .stream()
                  .collect(Collectors.joining(divider));
     }

}
