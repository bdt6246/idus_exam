package com.example.idus_exam.user;

import com.example.idus_exam.user.model.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
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
}
