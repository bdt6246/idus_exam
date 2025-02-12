package com.example.idus_exam.user;

import com.example.idus_exam.order.model.Orders;
import com.example.idus_exam.order.model.OrdersDto;
import com.example.idus_exam.user.model.User;
import com.example.idus_exam.user.model.UserDto;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.idus_exam.order.model.QOrders.orders;
import static com.example.idus_exam.user.model.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {
  private final JPAQueryFactory queryFactory;
  @Override
  public List<UserDto.UserOrdersListResponse> findUsersWithLatestOrder() {
    List<Tuple> results = queryFactory
        .select(user, orders)
        .from(user)
        .join(orders).on(orders.user.eq(user))
        .where(orders.orderDate.eq(
            queryFactory.select(orders.orderDate.max())
                .from(orders)
                .where(orders.user.eq(user))
        ))
        .fetch();

    return results.stream()
        .map(tuple -> {
          User u = tuple.get(user);
          Orders latestOrder = tuple.get(orders);

          return UserDto.UserOrdersListResponse.builder()
              .idx(u.getIdx())
              .userName(u.getUsername())
              .orders(List.of(OrdersDto.OrdersResponse.from(latestOrder)))
              .build();
        })
        .collect(Collectors.toList());
  }
}
