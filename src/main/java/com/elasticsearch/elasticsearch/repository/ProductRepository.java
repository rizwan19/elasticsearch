package com.elasticsearch.elasticsearch.repository;

import com.elasticsearch.elasticsearch.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    Iterable<Product> findByNameContaining(String name);
}
