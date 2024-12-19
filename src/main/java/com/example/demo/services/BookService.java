package com.example.demo.services;

import com.example.demo.dto.BookDTO;
import com.example.demo.entities.Book;
import com.example.demo.mappers.BookMapper;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public BookDTO updateBook(Long id, BookDTO updatedBookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Mappage entre BookDTO et Book
        Book bookToUpdate = BookMapper.INSTANCE.bookDTOToBook(updatedBookDTO);

        book.setAuthor(bookToUpdate.getAuthor());
        book.setAvailable(bookToUpdate.getAvailable());
        book.setCategory(bookToUpdate.getCategory());
        book.setDescription(bookToUpdate.getDescription());
        book.setLanguage(bookToUpdate.getLanguage());
        book.setTitle(bookToUpdate.getTitle());

        return BookMapper.INSTANCE.bookToBookDTO(bookRepository.save(book));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
