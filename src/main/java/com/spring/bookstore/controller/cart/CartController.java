package com.spring.bookstore.controller.cart;

import com.spring.bookstore.model.User;
import com.spring.bookstore.model.book.Book;
import com.spring.bookstore.model.cart.CartItem;
import com.spring.bookstore.service.UserService;
import com.spring.bookstore.service.book.BookService;
import com.spring.bookstore.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final BookService bookService;
    private final UserService userService;

    @GetMapping
    public String viewCart(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<CartItem> cartItems = cartService.getCartItemsByUser(user);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    @PostMapping("/add/{bookId}")
    public String addToCart(@PathVariable Long bookId, @RequestParam int quantity, Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookService.getBookById(bookId);

        try {
            cartService.addToCart(user, book, quantity);
            model.addAttribute("successMessage", "Item added to cart successfully");
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/cart";
    }


    @PostMapping("/remove/{itemId}")
    public String removeFromCart(@PathVariable Long itemId) {
        cartService.removeCartItem(itemId);
        return "redirect:/cart";
    }

}
