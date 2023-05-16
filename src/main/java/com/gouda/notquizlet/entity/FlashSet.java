package com.gouda.notquizlet.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class FlashSet {
    @Id
    @GeneratedValue
    private long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Flashcard> flashcards = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private final User owner;
    //private List<Integer> starred;
    //private String description;
    //private List<Tag> tags;
    //TODO: impl later

    public FlashSet(long id, List<Flashcard> flashcards, User owner) {
        this.id = id;
        this.flashcards = flashcards;
        this.owner = owner;
    }
    public FlashSet(User owner) {

        this.owner = owner;
    }

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

    public User getOwner() {
        return owner;
    }
}
