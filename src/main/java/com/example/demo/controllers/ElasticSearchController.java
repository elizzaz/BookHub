package com.example.demo.controllers;

import com.example.demo.entities.Book;
import com.example.demo.services.ElasticSearchService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/els")
public class ElasticSearchController {

    private final ElasticSearchService elasticSearchService;

    public ElasticSearchController(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

    @PostMapping("/reindex")
    public String reindexBooks() {
        try {
            elasticSearchService.indexAllBooks();
            return "Réindexation réussie !";
        } catch (IOException e) {
            return "Erreur lors de la réindexation : " + e.getMessage();
        }
    }

    @GetMapping("/bytitle")
    public List<Book> searchBooksByTitle(@RequestParam String title) throws IOException {
        return elasticSearchService.searchBooksByTitle(title);
    }

   // TODO A corriger
//    @GetMapping("/searchByCategories")
//    public List<Book> searchBooksByCategories(@RequestParam Set<String> categories) {
//        try {
//            return elasticSearchService.searchBooksByCategories(categories);
//        } catch (IOException e) {
//            throw new RuntimeException("Search failed", e);
//        }
//    }
}
