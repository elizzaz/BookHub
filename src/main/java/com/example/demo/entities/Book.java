package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private String author;
    @Lob
    @Size(max = 1000000)
    private String description;
    @Enumerated(EnumType.ORDINAL)
        private Category category;
    private Boolean available;
    private String language;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public Boolean getAvailable() {
        return available;
    }
    public String getLanguage(){
        return language;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
