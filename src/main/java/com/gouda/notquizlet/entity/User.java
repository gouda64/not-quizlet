package com.gouda.notquizlet.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlashSet> sets;
    private String username;
    private String password;
    private String email;

    public User(long id, String username, String password, List<FlashSet> sets, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sets = sets;
        this.email = email;
    }
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<FlashSet> getSets() {
        return sets;
    }

    public void setSets(List<FlashSet> sets) {
        this.sets = sets;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
