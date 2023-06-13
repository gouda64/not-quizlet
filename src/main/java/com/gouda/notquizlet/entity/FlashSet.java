package com.gouda.notquizlet.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class FlashSet {
    @Column(name = "id")
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cards")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Flashcard> flashcards = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner; //final?? how tf does spring boot work
    //private List<Integer> starred;
    //private String description;
    //private List<Tag> tags;
    //TODO: impl later


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
