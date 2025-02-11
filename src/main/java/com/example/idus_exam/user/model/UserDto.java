package com.example.idus_exam.user.model;

import com.example.idus_exam.order.model.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDto {
  @Getter
  public static class SignupRequest {
    private String userName;
    private String nickName;
    private String password;
    private Long phone;
    private String email;
    private String sex;

    public User toEntity(String encodedPassword) {
      return User.builder()
            .userName(userName)
            .nickName(nickName)
            .password(encodedPassword)
            .phone(phone)
            .email(email)
            .sex(sex)
            .role("USER")
            .build();
    }
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class UserDetailResponse {
    private String userName;
    private String nickName;
    private Long phone;
    private String email;
    private String sex;
    public static UserDetailResponse from(User user) {
      return UserDetailResponse.builder()
          .userName(user.getUsername())
          .nickName(user.getNickName())
          .phone(user.getPhone())
          .email(user.getEmail())
          .sex(user.getSex())
          .build();
    }
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class UserOrderListResponse {
    private Long idx;
    private String userName;
    List<OrderDto.OrderResponse> orders = new ArrayList<>();

    public static UserOrderListResponse from (User user){
      return UserOrderListResponse.builder()
          .idx(user.getIdx())
          .userName(user.getUsername())
          .orders(user.getOrderList().stream().map(OrderDto.OrderResponse::from).collect(Collectors.toList()))
          .build();
    }
  }
}
