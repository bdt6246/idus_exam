package com.example.idus_exam.user;

import com.example.idus_exam.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUserName(String username);
  @Query("SELECT DISTINCT u FROM User u JOIN u.orderList o ORDER BY o.orderDate DESC")
  Page<User> findAllOrderByOrderDateDesc(Pageable pageable);
}
