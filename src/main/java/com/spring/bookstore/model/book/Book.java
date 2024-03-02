package com.spring.bookstore.model.book;

import com.spring.bookstore.model.User;
import com.spring.bookstore.model.cart.CartItem;
import com.spring.bookstore.model.order.OrderItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private int ISBN;
    private double price;
    private int quantityInStock;
    private String image;
    private String genreOfTheBook;
    private String topicOfTheBook;
    @ManyToMany(mappedBy = "books")
    private Set<User> users = new HashSet<>();
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>();
}
