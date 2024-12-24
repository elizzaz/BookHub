package com.example.demo.controllers;

import com.example.demo.entities.Book;
import com.example.demo.services.BooksSearchService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class BooksSearchController {

    private final BooksSearchService booksSearchService;

    public BooksSearchController(BooksSearchService booksSearchService) {
        this.booksSearchService = booksSearchService;
    }

    @PostMapping("/reindex")
    public String reindexBooks() {
        try {
            booksSearchService.indexAllBooks();
            return "Réindexation réussie !";
        } catch (IOException e) {
            return "Erreur lors de la réindexation : " + e.getMessage();
        }
    }

    @GetMapping("/books/search")
    public List<Book> searchBooksByTitle(@RequestParam String title) throws IOException {
        return booksSearchService.searchBooksByTitle(title);
    }
}
