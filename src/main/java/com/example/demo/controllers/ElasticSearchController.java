package com.example.demo.controllers;

import com.example.demo.entities.Book;
import com.example.demo.services.ElasticSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> searchBooksByTitle(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<Book> results = elasticSearchService.searchBooksByTitle(title, page, size);

            if (results.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Aucun résultat trouvé.");
            }

            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur serveur.");
        }
    }

    @GetMapping("/searchByCategories")
    public ResponseEntity<?> searchBooksByCategories(
            @RequestParam Set<String> categories,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<Book> results = elasticSearchService.searchBooksByCategories(categories, page, size);

            if (results.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Aucun résultat trouvé.");
            }

            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur serveur.");
        }
    }


    @GetMapping("/fulltext")
    public ResponseEntity<?> searchFullText(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<Book> results = elasticSearchService.searchFullText(query, page, size);

            if (results.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Aucun résultat trouvé.");
            }

            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur serveur.");
        }
    }

}
