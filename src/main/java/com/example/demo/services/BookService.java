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


    private final BookRepository bookRepository;
    private final ElasticSearchService elasticSearchService;
    @Autowired
    private BookMapper bookMapper;

    public BookService(BookRepository bookRepository, ElasticSearchService elasticSearchService) {
        this.bookRepository = bookRepository;
        this.elasticSearchService = elasticSearchService;
    }

    public Book saveBook(Book book) {
        Book savedBook = bookRepository.save(book);
        elasticSearchService.indexBook(savedBook);
        return savedBook;
    }

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        elasticSearchService.indexBook(savedBook);
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

        bookMapper.updateBookFromDto(updatedBookDTO, book);

        Book updatedBook = bookRepository.save(book);

        elasticSearchService.indexBook(updatedBook);

        return bookMapper.toDto(updatedBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);
        elasticSearchService.deleteBook(id);
    }

}
