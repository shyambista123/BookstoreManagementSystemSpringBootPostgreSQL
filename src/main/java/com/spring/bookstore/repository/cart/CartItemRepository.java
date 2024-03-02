package com.spring.bookstore.repository.cart;

import com.spring.bookstore.model.User;
import com.spring.bookstore.model.book.Book;
import com.spring.bookstore.model.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByBook(Book book);

    Optional<CartItem> findByUserAndBook(User user, Book book);

    List<CartItem> findByUser(User user);
}
