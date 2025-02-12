package com.example.idus_exam.user;

import com.example.idus_exam.user.model.UserDto;

import java.util.List;

public interface UserCustomRepository {
  List<UserDto.UserOrdersListResponse> findUsersWithLatestOrder();
}