package com.spring.bookstore.controller.book;

import com.spring.bookstore.model.book.Book;
import com.spring.bookstore.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookOperationController {
    private final BookService bookService;
    private static String UPLOAD_DIR = "src/main/resources/static/images";
    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book, @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            if (!imageFile.isEmpty()) {
                String image = book.getTitle()+".png";
                Files.copy(imageFile.getInputStream(), Paths.get(UPLOAD_DIR, image));
                book.setImage(image);
            }
            bookService.saveBook(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/books";
    }


    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute Book updatedBook, @RequestParam("newImageFile") MultipartFile newImageFile) {
        Book existingBook = bookService.getBookById(id);

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setISBN(updatedBook.getISBN());
        existingBook.setPrice(updatedBook.getPrice());
        existingBook.setQuantityInStock(updatedBook.getQuantityInStock());
        try {
            if (!newImageFile.isEmpty()) {
                String image = existingBook.getTitle()+".png";
                Files.copy(newImageFile.getInputStream(), Paths.get(UPLOAD_DIR, image));
                existingBook.setImage(image);
            }
            bookService.saveBook(existingBook);
        } catch (IOException e) {
            e.printStackTrace();
        }

        bookService.saveBook(existingBook);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBookWithCartItems(id);
        return "redirect:/books";
    }
}
