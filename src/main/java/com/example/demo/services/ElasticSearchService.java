package com.example.demo.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.demo.entities.Book;
import com.example.demo.repositories.jpa.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ElasticSearchService {

    private final ElasticsearchClient elasticsearchClient;
    private final BookRepository bookRepository;

    @Autowired
    public ElasticSearchService(ElasticsearchClient elasticsearchClient, BookRepository bookRepository) {
        this.elasticsearchClient = elasticsearchClient;
        this.bookRepository = bookRepository;
    }

    // automatisation de l'indexation des livres
    public void indexBook(Book book){
        try {
            elasticsearchClient.index(IndexRequest.of(i -> i
                    .index("books")
                    .id(String.valueOf(book.getId()))
                    .document(book)
            ));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Méthode pour réindexer tous les livres manuellement
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

    public void deleteBook(Long id) {
        try {
            elasticsearchClient.delete(DeleteRequest.of(d -> d
                    .index("books")
                    .id(String.valueOf(id))
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Book> searchBooksByTitle(String title,
                                         int page,
                                         int size) throws IOException {
        // request match pour la recherche full-text
        Query query = MatchQuery.of(m -> m
                        .field("title")  // Champ
                        .query(title))  // Valeur
                ._toQuery();

        SearchResponse<Book> response = elasticsearchClient.search(s -> s
                        .index("books") // index els
                        .query(query)
                        .from(page * size)
                        .size(size),
                Book.class);

        return response.hits().hits().stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }


    public List<Book> searchBooksByCategories(Set<String> categories,
                                              int page,
                                              int size) throws IOException {
        Query query = MatchQuery.of(m -> m
                .field("categories")
                .query(String.join(" ", categories))
        )._toQuery();

        SearchResponse<Book> response = elasticsearchClient.search(s -> s
                        .index("books")
                        .query(query)
                        .from(page * size)
                        .size(size),
                Book.class);

        return response.hits().hits().stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }


    public List<Book> searchFullText(String queryText,
                                     int page,
                                     int size) throws IOException {
        Query query = Query.of(q -> q
                .multiMatch(MultiMatchQuery.of(m -> m
                        .query(queryText) // Texte à rechercher
                        .fields("title^3", "author^2", "description")
                )));


        SearchResponse<Book> response = elasticsearchClient.search(s -> s
                        .index("books")
                        .query(query)
                        .from(page * size)
                        .size(size),
                Book.class);

        return response.hits().hits().stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

}
