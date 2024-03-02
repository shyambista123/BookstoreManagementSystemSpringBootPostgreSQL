package com.spring.bookstore.dto;

public record AdminRegistration(String firstName, String lastName, String email, String password, String role){
    public AdminRegistration(String firstName, String lastName, String email, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = "ADMIN";
    }
}
