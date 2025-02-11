package com.example.idus_exam.user;

import com.example.idus_exam.emailverify.EmailVerifyService;
import com.example.idus_exam.user.model.User;
import com.example.idus_exam.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final EmailVerifyService emailVerifyService;

  @Transactional
  public void signup(UserDto.SignupRequest dto) {
    String encodedPassword = passwordEncoder.encode(dto.getPassword());
    User user = userRepository.save(dto.toEntity(encodedPassword));
    emailVerifyService.signup(user.getIdx(), user.getEmail());
  }

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> result = userRepository.findByUserName(username);
    return result.orElse(null);
  }

  @Transactional
  public void verify(String uuid) {
    User user = emailVerifyService.verify(uuid);
    if (user != null) {
      user.verify();
      userRepository.save(user);
    }
  }

  @Transactional(readOnly = true)
  public UserDto.UserDetailResponse detail(Long idx) {
    Optional<User> user = userRepository.findById(idx);
    return UserDto.UserDetailResponse.from(user.orElse(null));
  }

  @Transactional(readOnly = true)
  public UserDto.UserOrderListResponse orderList(Long userIdx) {
    Optional<User> user = userRepository.findById(userIdx);
    return UserDto.UserOrderListResponse.from(user.orElse(null));
  }

//  @Transactional(readOnly = true)
//  public UserDto.UserPageResponse list(int page, int size) {
//    Page<User> result = userRepository.findAllOrderByOrderDateDesc(PageRequest.of(page, size));
//    return UserDto.UserPageResponse.from(result);
//  }

  @Transactional(readOnly = true)
  public UserDto.UserPageResponse list(int page, int size) {
    Page<User> result = userRepository.findAll(PageRequest.of(page, size));
    return UserDto.UserPageResponse.from(result);
  }

  public List<UserDto.UserDetailResponse> searchByName(String userName) {
    List<UserDto.UserDetailResponse> userList = userRepository.findAllByUserNameContaining(userName);
    return userList;
  }

  public List<UserDto.UserDetailResponse> searchByEmail(String email) {
    List<UserDto.UserDetailResponse> userList = userRepository.findAllByUserEmailContaining(email);
    return userList;
  }
}
