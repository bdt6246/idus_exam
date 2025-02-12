package com.example.idus_exam.user.model;

import com.example.idus_exam.order.model.OrdersDto;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.NumberFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDto {
  @Getter
  public static class SignupRequest {
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z]+$", message = "이름은 한글과 영문 대소문자만 입력 가능")
    private String userName;
    @Pattern(regexp = "^[a-z]+$", message = "영문 소문자만 허용")
    private String nickName;
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).+$",
        message = "비밀번호는 영문 대문자, 소문자, 숫자, 특수문자를 각각 1개 이상 포함해야 합니다."
    )
    private String password;
    @NumberFormat
    private Long phone;
    @Email
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
