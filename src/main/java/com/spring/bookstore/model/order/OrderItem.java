package com.spring.bookstore.model.order;

import com.spring.bookstore.model.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserOrder order;

    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;

    private int quantity;
    private Double totalPrice;
}
