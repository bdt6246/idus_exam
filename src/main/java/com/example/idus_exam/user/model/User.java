package com.example.idus_exam.user.model;

import com.example.idus_exam.emailverify.model.EmailVerify;
import com.example.idus_exam.order.model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idx;
  private String userName;
  private String nickName;
  private String password;
  private Long phone;
  private String email;
  private String sex;
  private String role;

  @OneToMany(mappedBy = "user")
  private List<Order> orderList = new ArrayList<>();

  @Override
  public String getUsername() { return userName; }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role);
    authorities.add(authority);
    return authorities;
  }
}
