package com.example.demo.controllers;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.ReviewDTO;
import com.example.demo.entities.Review;
import com.example.demo.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Review createReview(@Valid @RequestBody Review review) {
        return reviewService.createReview(review);
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @PutMapping("/{id}")
    public ReviewDTO updateReview(@PathVariable Long id, @RequestBody ReviewDTO updatedReviewDTO) {
        return reviewService.updateReview(id, updatedReviewDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}

