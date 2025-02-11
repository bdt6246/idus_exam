package com.example.idus_exam.user;

import com.example.idus_exam.user.model.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  @Operation(summary = "회원 가입")
  @PostMapping("/signup")
  public void signup(@RequestBody UserDto.SignupRequest dto) {
    userService.signup(dto);
  }

  @Operation(summary = "유저 상세 조회")
  @GetMapping("/{userIdx}")
  public ResponseEntity<UserDto.UserDetailResponse> detail(@PathVariable Long userIdx) {
    UserDto.UserDetailResponse response = userService.detail(userIdx);
    return ResponseEntity.ok(response);
  }

  @Operation(summary = "주문 목록 조회")
  @GetMapping("/order")
  public ResponseEntity<UserDto.UserOrderListResponse> orderList(Long userIdx) {
    UserDto.UserOrderListResponse response =  userService.orderList(userIdx);
    return ResponseEntity.ok(response);
  }

//  @GetMapping("/list")
//  @Operation(summary = "각 회원의 마지막 주문 정보 조회")
//  public ResponseEntity<UserDto.UserPageResponse> list(int page, int size) {
//    UserDto.UserPageResponse response = userService.list(page, size);
//    return ResponseEntity.ok(response);
//  }
  @GetMapping("/list")
  @Operation(summary = "여러 회원 목록 조회", description = "페이지네이션으로 일정 단위로 조회")
  public ResponseEntity<UserDto.UserPageResponse> list(int page, int size) {
    UserDto.UserPageResponse response = userService.list(page, size);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/search")
  @Operation(summary = "이름을 이용한 검색")
  public ResponseEntity<List<UserDto.UserDetailResponse>> searchByName(String userName){
    List<UserDto.UserDetailResponse> response = userService.searchByName(userName);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/search")
  @Operation(summary = "이메일을 이용한 검색")
  public ResponseEntity<List<UserDto.UserDetailResponse>> searchByEmail(String email){
    List<UserDto.UserDetailResponse> response = userService.searchByEmail(email);
    return ResponseEntity.ok(response);
  }
}
