package com.example.demo.mappers;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    ReviewDTO toDto(Review review);
    Review toEntity(ReviewDTO reviewDTO);
}
