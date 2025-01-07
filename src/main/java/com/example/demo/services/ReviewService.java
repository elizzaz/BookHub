package com.example.demo.services;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.entities.Review;
import com.example.demo.mappers.ReviewMapper;
import com.example.demo.repositories.jpa.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review = reviewMapper.toEntity(reviewDTO);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toDto(savedReview);
    }

    public ReviewDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return reviewMapper.toDto(review);
    }

    public ReviewDTO updateReview(Long id, ReviewDTO updatedReviewDTO) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        Review reviewToUpdate = ReviewMapper.INSTANCE.toEntity(updatedReviewDTO);

        review.setRating(reviewToUpdate.getRating());
        review.setComment(reviewToUpdate.getComment());
        review.setBook(reviewToUpdate.getBook());
        review.setUser(reviewToUpdate.getUser());

        return ReviewMapper.INSTANCE.toDto(reviewRepository.save(review));
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
