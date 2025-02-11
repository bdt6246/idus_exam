package com.example.idus_exam.user;

import com.example.idus_exam.user.model.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  @Operation(summary = "이메일 인증")
  @GetMapping("/verify")
  public void verify(String uuid) {
    userService.verify(uuid);
  }

  @Operation(summary = "회원 가입")
  @PostMapping("/signup")
  public void signup(@RequestBody UserDto.SignupRequest dto) {
    userService.signup(dto);
  }

  @Operation(summary = "유저 상세 조회")
  @GetMapping("/{userIdx}")
  public ResponseEntity<UserDto.UserDetailResponse> detail(@PathVariable Long userIdx) {
    UserDto.UserDetailResponse response = userService.list(userIdx);
    return ResponseEntity.ok(response);
  }

  @Operation(summary = "주문 목록 조회")
  @GetMapping("/order")
  public ResponseEntity<UserDto.UserOrderListResponse> orderList(Long userIdx) {
    UserDto.UserOrderListResponse response =  userService.orderList(userIdx);
    return ResponseEntity.ok(response);
  }

//  @GetMapping("/list")
//  @Operation(summary = "회원 목록 조회")
//  public ResponseEntity<UserDto.CoursePageResponse> list(int page, int size) {
//    CourseDto.CoursePageResponse response = courseService.list(page, size);
//
//    return ResponseEntity.ok(response);
//  }

}
