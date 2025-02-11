package com.example.idus_exam.user.model;

import lombok.Getter;

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
}
