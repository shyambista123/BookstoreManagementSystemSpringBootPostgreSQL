package com.spring.bookstore.service.book;

import com.spring.bookstore.model.book.Book;
import com.spring.bookstore.model.cart.CartItem;
import com.spring.bookstore.model.order.OrderItem;
import com.spring.bookstore.repository.book.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookWithCartItems(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        for (CartItem cartItem : book.getCartItems()) {
            cartItem.setBook(null);
        }
        for (OrderItem orderItem : book.getOrderItems()) {
            orderItem.setBook(null);
        }
        bookRepository.deleteById(bookId);
    }
}
