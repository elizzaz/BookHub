package com.example.demo.repositories.elasticsearch;

import com.example.demo.entities.Book;
import com.example.demo.entities.Category;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Set;

public interface ElasticBookSearchRepository extends ElasticsearchRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
//    List<Book> findByCategory(Set<Category> categories);
}