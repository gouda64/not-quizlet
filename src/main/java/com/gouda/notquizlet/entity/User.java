package com.gouda.notquizlet.entity;

import jakarta.persistence.*;

import java.util.Set;
import java.util.TreeSet;

@Entity
public class User {
    @Column(name = "id")
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "sets")
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FlashSet> sets;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<FlashSet> getSets() {
        return sets;
    }

    public void setSets(TreeSet<FlashSet> sets) {
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
}
