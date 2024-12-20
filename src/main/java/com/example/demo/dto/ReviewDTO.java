package com.example.demo.dto;

import com.example.demo.entities.Book;
import com.example.demo.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Size;

public class ReviewDTO {
    private int rating;
    @Lob
    @Size(max = 1000000)
    private String comment;
    private User user;
    private Book book;


    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
