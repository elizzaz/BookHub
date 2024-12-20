package com.example.demo.dto;

import com.example.demo.entities.Book;
import com.example.demo.entities.User;

public class ReviewDTO {
    private int rating;
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
}
