package com.example.idus_exam.user.model;

import com.example.idus_exam.order.model.OrdersDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

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
  public static class UserOrdersListResponse {
    private Long idx;
    private String userName;
    List<OrdersDto.OrdersResponse> orders = new ArrayList<>();

    public static UserOrdersListResponse from (User user){
      return UserOrdersListResponse.builder()
          .idx(user.getIdx())
          .userName(user.getUsername())
          .orders(user.getOrdersList().stream().map(OrdersDto.OrdersResponse::from).collect(Collectors.toList()))
          .build();
    }
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class  UserPageResponse{
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;
    private List<UserDetailResponse> users;

    public static UserPageResponse from (Page<User> userPage) {
      return UserPageResponse.builder()
          .page(userPage.getNumber())
          .size(userPage.getSize())
          .totalElements(userPage.getTotalElements())
          .totalPages(userPage.getTotalPages())
          .hasNext(userPage.hasNext())
          .hasPrevious(userPage.hasPrevious())
          .users(userPage.stream().map(UserDetailResponse::from).collect(Collectors.toList()))
          .build();
    }
  }
}
