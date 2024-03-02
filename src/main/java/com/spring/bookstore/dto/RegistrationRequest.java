package com.spring.bookstore.dto;

public record RegistrationRequest (String firstName, String lastName, String email, String password, String role){
    public RegistrationRequest(String firstName, String lastName, String email, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = "USER";
    }
}
