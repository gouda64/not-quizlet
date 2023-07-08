package com.gouda.notquizlet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class FlashSet {
    @Column(name = "id")
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "cards")
    @OneToMany(cascade = CascadeType.ALL)
    @NotEmpty
    private List<Flashcard> flashcards = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    @NotNull
    private User owner; //final?? how tf does spring boot work
    //private List<Integer> starred;
    //private String description;
    //private List<Tag> tags;
    //TODO: impl later

    @Column(name = "enabled")
    private boolean enabled;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Flashcard> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(List<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
