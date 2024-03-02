package com.spring.bookstore.service.cart;

import com.spring.bookstore.model.User;
import com.spring.bookstore.model.book.Book;
import com.spring.bookstore.model.cart.CartItem;
import com.spring.bookstore.repository.cart.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService{
    private final CartItemRepository cartItemRepository;
    @Override
    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public void addToCart(User user, Book book, int quantity) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findByUserAndBook(user, book);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            int newQuantity = cartItem.getQuantity() + quantity;

            if (newQuantity <= book.getQuantityInStock()) {
                cartItem.setQuantity(newQuantity);
            } else {
                throw new RuntimeException("Cannot add more than available stock");
            }
        } else {
            if (quantity <= book.getQuantityInStock()) {
                CartItem cartItem = new CartItem();
                cartItem.setBook(book);
                cartItem.setQuantity(quantity);
                cartItem.setUser(user);
                cartItemRepository.save(cartItem);
            } else {
                throw new RuntimeException("Cannot add more than available stock");
            }
        }
    }

    @Override
    public List<CartItem> getCartItemsByUser(User user) {
        return cartItemRepository.findByUser(user);
    }


    @Override
    public void removeCartItem(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }
}
