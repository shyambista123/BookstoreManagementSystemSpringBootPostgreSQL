package com.spring.bookstore.repository.book;

import com.spring.bookstore.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
