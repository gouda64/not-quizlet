package com.gouda.notquizlet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Flashcard {
    @Id
    @GeneratedValue
    private long id;
    private String term;
    private String definition;

    public Flashcard(long id, String term, String definition) {
        this.id = id;
        this.term = term;
        this.definition = definition;
    }
    public Flashcard(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
