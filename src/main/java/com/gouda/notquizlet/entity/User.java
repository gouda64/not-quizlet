package com.gouda.notquizlet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
public class User {
    @Column(name = "id", unique = true)
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "sets")
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FlashSet> sets;

    @Column(name = "username", unique = true)
    @NotNull
    @NotBlank
    private String username;

    @Column(name = "password")
    private String password;

    private String passwordMatching;

    @Column(name = "email", unique = true)
    @Email
    @NotNull
    @NotBlank
    private String email;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    private boolean enabled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<FlashSet> getSets() {
        return sets;
    }

    public void setSets(Set<FlashSet> sets) {
        this.sets = sets;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordMatching() {
        return passwordMatching;
    }

    public void setPasswordMatching(String passwordMatching) {
        this.passwordMatching = passwordMatching;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
