package com.example.demo.dto;

import com.example.demo.entities.Category;

public class BookDTO {
    private String title;
    private String author;
    private String description;
    private String language;
    private Boolean available;
    private Category category;

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
    }}

