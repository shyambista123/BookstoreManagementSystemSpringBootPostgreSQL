package com.spring.bookstore.service.book;

import com.spring.bookstore.model.book.Book;

import java.util.List;

public interface BookService {
        List<Book> getAllBooks();
        Book getBookById(Long id);
        Book saveBook(Book book);

    void deleteBookWithCartItems(Long bookId);
}
