package com.spring.bookstore.controller;

import com.spring.bookstore.dto.AdminRegistration;
import com.spring.bookstore.dto.RegistrationRequest;
import com.spring.bookstore.event.RegistrationCompleteEvent;
import com.spring.bookstore.exception.UserAlreadyExistsException;
import com.spring.bookstore.model.User;
import com.spring.bookstore.repository.token.VerificationTokenRepository;
import com.spring.bookstore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class AuthController {
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository verificationTokenRepository;

    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(UserAlreadyExistsException.class)
        public String handleUserAlreadyExistsException(UserAlreadyExistsException ex, Model model) {
            model.addAttribute("user", new RegistrationRequest("","","","",""));
            model.addAttribute("admin",new AdminRegistration("","","","",""));
            model.addAttribute("errorMessage", ex.getMessage()); // Add the error message to the model
            return "register";
        }
    }


    @GetMapping
    public String showRegistrationPage(Model model){
        model.addAttribute("user",new RegistrationRequest("","","","",""));
        return "register";
    }

    @GetMapping("/admin")
    public String showAdminRegistrationPage(Model model){
        model.addAttribute("user", new AdminRegistration("", "", "", "", ""));
        return "adminRegister";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") @RequestBody RegistrationRequest registrationRequest,final HttpServletRequest request){
        User user = userService.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request)));
//        return "Success you have registered successfully check you email to verify email";
        return "redirect:/register?success";
    }
    @PostMapping("/admin")
    public String registerAdmin(@ModelAttribute("user") @RequestBody AdminRegistration adminRegistration, final HttpServletRequest request) {
        User user = userService.registerAdmin(adminRegistration);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "redirect:/register?success";
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token, Model model){
    String verificationResult = userService.validateToken(token);

    if (verificationResult.equalsIgnoreCase("Valid")){
        model.addAttribute("verificationResult", "Email verified successfully. Now you can login to your account.");
    } else if (verificationResult.equalsIgnoreCase("Token expired")) {
        model.addAttribute("verificationResult", "The verification link has expired. Please request a new one.");
    } else {
        model.addAttribute("verificationResult", "Invalid verification token");
    }

    return "verify-email-result";
}

    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}
