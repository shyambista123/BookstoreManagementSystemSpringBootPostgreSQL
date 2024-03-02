package com.spring.bookstore.service;


import com.spring.bookstore.dto.AdminRegistration;
import com.spring.bookstore.dto.RegistrationRequest;
import com.spring.bookstore.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUser();
    User registerUser(RegistrationRequest request);

    User registerAdmin(AdminRegistration request);

    Optional<User> findByEmail(String email);
    void saveVerificationToken(User theUser,String token);
    String validateToken(String theToken);
}
