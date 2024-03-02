package com.spring.bookstore.service.cart;

import com.spring.bookstore.model.User;
import com.spring.bookstore.model.book.Book;
import com.spring.bookstore.model.cart.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> getCartItems();
    void addToCart(User user, Book book, int quantity);
    List<CartItem> getCartItemsByUser(User user);
    void removeCartItem(Long itemId);

}
