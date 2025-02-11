package com.example.idus_exam.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

public class OrderDto {

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class OrderResponse {
    private String orderNo;
    private String productName;
    private ZonedDateTime orderDate;

    public static OrderResponse from(Order order) {
      return OrderResponse.builder()
          .orderNo(order.getOderNo())
          .productName(order.getProductName())
          .orderDate(order.getOrderDate())
          .build();
    }
  }
}
