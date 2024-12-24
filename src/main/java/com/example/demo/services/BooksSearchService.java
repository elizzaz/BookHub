package com.example.demo.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.demo.entities.Book;
import com.example.demo.repositories.jpa.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksSearchService {

    private final ElasticsearchClient elasticsearchClient;
    private final BookRepository bookRepository;

    @Autowired
    public BooksSearchService(ElasticsearchClient elasticsearchClient, BookRepository bookRepository) {
        this.elasticsearchClient = elasticsearchClient;
        this.bookRepository = bookRepository;
    }

    // Méthode pour réindexer tous les livres pour l'instant non syncro
    public void indexAllBooks() throws IOException {
        List<Book> books = bookRepository.findAll();

        for (Book book : books) {
            elasticsearchClient.index(i -> i
                    .index("books")
                    .id(String.valueOf(book.getId()))
                    .document(book)
            );
        }
    }

//    public List<Book> searchBooksByTitle(String title) throws IOException {
//        var response = elasticsearchClient.search(s -> s
//                .index("books")
//                .query(q -> q
//                        .match(m -> m
//                                .field("title")
//                                .query(title)
//                        )
//                ), Book.class);
//
//        return response.hits().hits().stream()
//                .map(hit -> hit.source())
//                .toList();
//    }

    public List<Book> searchBooksByTitle(String title) throws IOException {
        // request match pour la recherche full-text
        Query query = MatchQuery.of(m -> m
                        .field("title")  // Champ
                        .query(title))  // Valeur
                ._toQuery();

        SearchResponse<Book> response = elasticsearchClient.search(s -> s
                        .index("books") // index els
                        .query(query),
                Book.class);

        return response.hits().hits().stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }
}
