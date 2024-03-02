package com.spring.bookstore.service.order;

import com.spring.bookstore.model.User;
import com.spring.bookstore.model.book.Book;
import com.spring.bookstore.model.cart.CartItem;
import com.spring.bookstore.model.order.OrderItem;
import com.spring.bookstore.model.order.UserOrder;
import com.spring.bookstore.repository.book.BookRepository;
import com.spring.bookstore.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    @Override
    public UserOrder placeOrder(User user, CartItem cartItem) {
        UserOrder order = new UserOrder();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());

        Double totalAmount = cartItem.getBook().getPrice() * cartItem.getQuantity();
        order.setTotalAmount(totalAmount);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setBook(cartItem.getBook());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setTotalPrice(cartItem.getBook().getPrice() * cartItem.getQuantity());
        order.getOrderItems().add(orderItem);

        Book book = cartItem.getBook();
        int newQuantityInStock = book.getQuantityInStock() - cartItem.getQuantity();
        book.setQuantityInStock(newQuantityInStock);

        orderRepository.save(order);
        bookRepository.save(book);

        return order;
    }



    @Override
    public List<UserOrder> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public void cancelOrder(UserOrder order) {
        List<OrderItem> orderItems = order.getOrderItems();

        orderRepository.delete(order);

        for (OrderItem orderItem : orderItems) {
            Book book = orderItem.getBook();
            int quantityOrdered = orderItem.getQuantity();
            int newQuantityInStock = book.getQuantityInStock() + quantityOrdered;
            book.setQuantityInStock(newQuantityInStock);

            bookRepository.save(book);
        }
    }

    @Override
    public UserOrder getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

}
