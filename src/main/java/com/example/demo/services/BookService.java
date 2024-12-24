package com.example.demo.services;

import com.example.demo.dto.BookDTO;
import com.example.demo.entities.Book;
import com.example.demo.mappers.BookMapper;
import com.example.demo.repositories.jpa.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public BookDTO createBook(BookDTO reviewDTO) {
        Book review = bookMapper.toEntity(reviewDTO);
        Book savedBook = bookRepository.save(review);
        return bookMapper.toDto(savedBook);
    }

    public BookDTO getBookById(Long id) {
        Book review = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return bookMapper.toDto(review);
    }

    public BookDTO updateBook(Long id, BookDTO updatedBookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Book bookToUpdate = BookMapper.INSTANCE.toEntity(updatedBookDTO);

        book.setAuthor(bookToUpdate.getAuthor());
        book.setAvailable(bookToUpdate.getAvailable());
        book.setCategories(bookToUpdate.getCategories());
        book.setDescription(bookToUpdate.getDescription());
        book.setLanguage(bookToUpdate.getLanguage());
        book.setTitle(bookToUpdate.getTitle());

        return BookMapper.INSTANCE.toDto(bookRepository.save(book));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
