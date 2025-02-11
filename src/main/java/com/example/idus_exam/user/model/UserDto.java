package com.example.idus_exam.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
