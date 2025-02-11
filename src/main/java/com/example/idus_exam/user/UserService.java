package com.example.idus_exam.user;

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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void signup(UserDto.SignupRequest dto) {
    String encodedPassword = passwordEncoder.encode(dto.getPassword());
    User user = userRepository.save(dto.toEntity(encodedPassword));
  }

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> result = userRepository.findByUserName(username);
    return result.orElse(null);
  }

  @Transactional(readOnly = true)
  public UserDto.UserDetailResponse detail(Long idx) {
    Optional<User> user = userRepository.findById(idx);
    return UserDto.UserDetailResponse.from(user.orElse(null));
  }

  @Transactional(readOnly = true)
  public UserDto.UserOrdersListResponse ordersList(Long userIdx) {
    Optional<User> user = userRepository.findById(userIdx);
    return UserDto.UserOrdersListResponse.from(user.orElse(null));
  }

  @Transactional(readOnly = true)
  public UserDto.UserPageResponse list(int page, int size) {
    Page<User> result = userRepository.findAll(PageRequest.of(page, size));
    return UserDto.UserPageResponse.from(result);
  }

  @Transactional(readOnly = true)
  public List<UserDto.UserDetailResponse> searchByName(String userName) {
    List<UserDto.UserDetailResponse> userList = userRepository.findAllByUserNameContaining(userName);
    return userList;
  }

  @Transactional(readOnly = true)
  public List<UserDto.UserDetailResponse> searchByEmail(String email) {
    List<UserDto.UserDetailResponse> userList = userRepository.findAllByEmailContaining(email);
    return userList;
  }

  @Transactional(readOnly = true)
  public List<UserDto.UserOrdersListResponse> lastOrdersList() {
    List<User> users = userRepository.findAllOrderByOrdersDateDesc();
    return users.stream()
        .map(UserDto.UserOrdersListResponse::from)
        .collect(Collectors.toList());
  }
}
