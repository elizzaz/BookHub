package com.example.demo.mappers;

import com.example.demo.dto.BookDTO;
import com.example.demo.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO toDto(Book book);
    Book toEntity(BookDTO bookDTO);

    void updateBookFromDto(BookDTO dto, @MappingTarget Book entity);
}
