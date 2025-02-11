package com.example.idus_exam.order.model;

import com.example.idus_exam.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Orders {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idx;
  private String orderNo;
  private String productName;
  private ZonedDateTime orderDate;

  @ManyToOne
  @JoinColumn(name = "user_idx")
  private User user;
}