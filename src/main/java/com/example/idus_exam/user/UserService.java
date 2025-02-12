package com.example.idus_exam.user;

import com.example.idus_exam.order.model.Orders;
import com.example.idus_exam.order.model.OrdersDto;
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

import java.util.*;
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
    List<User> users = userRepository.findAllByUserNameContaining(userName);
    return users.stream()
        .map(user -> new UserDto.UserDetailResponse(user.getUsername(), user.getNickName(), user.getPhone(), user.getEmail(), user.getSex()))
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<UserDto.UserDetailResponse> searchByEmail(String email) {
    List<User> users = userRepository.findAllByEmailContaining(email);
    return users.stream()
        .map(user -> new UserDto.UserDetailResponse(user.getUsername(), user.getNickName(), user.getPhone(), user.getEmail(), user.getSex()))
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<UserDto.UserOrdersListResponse> lastOrdersList() {
      return userRepository.findUsersWithLatestOrder();

//    return userRepository.findAll().stream()
//        .map(user -> {
//          Orders latestOrder = user.getOrdersList().stream()
//              .max(Comparator.comparing(Orders::getOrderDate))
//              .orElse(null);
//
//          return latestOrder != null
//              ? UserDto.UserOrdersListResponse.builder()
//              .idx(user.getIdx())
//              .userName(user.getUsername())
//              .orders(Collections.singletonList(OrdersDto.OrdersResponse.from(latestOrder))) // 최신 주문만 리스트로
//              .build()
//              : null;
//        })
//        .filter(Objects::nonNull)
//        .collect(Collectors.toList());
  }
}
