package com.elasticsearch.elasticsearch.service;

import com.elasticsearch.elasticsearch.model.Product;
import com.elasticsearch.elasticsearch.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    public Iterable<Product> searchByName(String name) {
        return productRepository.findByNameContaining(name);
    }
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }
}
