package com.example.idus_exam.user;

import com.example.idus_exam.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
  Optional<User> findByUserName(String username);
  List<User> findAllByUserNameContaining(String username);
  List<User> findAllByEmailContaining(String email);
}