package com.example.demo.mappers;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.entities.Review;
import org.mapstruct.factory.Mappers;

public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    ReviewDTO reviewToReviewDTO(Review review);
    Review reviewDTOToReview(ReviewDTO reviewDTO);




}
