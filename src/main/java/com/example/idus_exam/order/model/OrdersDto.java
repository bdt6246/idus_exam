package com.example.idus_exam.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

public class OrdersDto {

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class OrdersResponse {
    private String ordersNo;
    private String productName;
    private ZonedDateTime ordersDate;

    public static OrdersResponse from(Orders orders) {
      return OrdersResponse.builder()
          .ordersNo(orders.getOrderNo())
              .productName(orders.getProductName())
              .ordersDate(orders.getOrderDate())
              .build();
    }
  }
}
