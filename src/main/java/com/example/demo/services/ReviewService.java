package com.example.demo.services;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.entities.Review;
import com.example.demo.mappers.ReviewMapper;
import com.example.demo.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public ReviewDTO updateReview(Long id, ReviewDTO updatedReviewDTO) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        // Mappage entre ReviewDTO et Review
        Review reviewToUpdate = ReviewMapper.INSTANCE.reviewDTOToReview(updatedReviewDTO);

        review.setRating(reviewToUpdate.getRating());
        review.setComment(reviewToUpdate.getComment());
        review.setBook(reviewToUpdate.getBook());
        review.setUser(reviewToUpdate.getUser());

        return ReviewMapper.INSTANCE.reviewToReviewDTO(reviewRepository.save(review));
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
