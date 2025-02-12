package com.example.idus_exam.order.model;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class OrdersDto {

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class OrdersResponse {
    private String ordersNo;
    @Pattern(regexp = "^[\\p{L}\\p{N}\\p{P}\\p{Z}\\p{S}\\p{M}]+$", message = "이모지를 포함한 모든 문자")
    private String productName;
    private LocalDateTime ordersDate;

    public static OrdersResponse from(Orders orders) {
      return OrdersResponse.builder()
          .ordersNo(orders.getOrderNo())
              .productName(orders.getProductName())
              .ordersDate(orders.getOrderDate())
              .build();
    }
  }
}
