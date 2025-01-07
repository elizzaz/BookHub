package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Set;

@Document(indexName = "books")
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
    @ElementCollection(targetClass = Category.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "book_categories", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "category")
    private Set<Category> categories;
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
    }
}
