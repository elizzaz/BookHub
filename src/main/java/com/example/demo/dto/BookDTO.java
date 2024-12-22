package com.example.demo.dto;

import com.example.demo.entities.Category;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class BookDTO {
    private String title;
    private String author;
    @Lob
    @Size(max = 1000000)
    private String description;
    private String language;
    private Boolean available;
    private Set<Category> categories;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public Set<Category> getCategories() {
        return categories;
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

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setLanguage(String language) {
        this.language = language;
    }}

