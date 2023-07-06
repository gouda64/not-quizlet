package com.gouda.notquizlet.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisteringUser {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordMatching;

    @Email
    @NotNull
    @NotBlank
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordMatching() {
        return passwordMatching;
    }

    public void setPasswordMatching(String passwordMatching) {
        this.passwordMatching = passwordMatching;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
