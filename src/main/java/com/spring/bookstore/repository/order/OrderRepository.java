package com.spring.bookstore.repository.order;


import com.spring.bookstore.model.User;
import com.spring.bookstore.model.order.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<UserOrder, Long> {
    List<UserOrder> findByUser(User user);
    Optional<UserOrder> findById(Long id);
}
