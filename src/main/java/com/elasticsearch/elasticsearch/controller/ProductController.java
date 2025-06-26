package com.elasticsearch.elasticsearch.controller;

import com.elasticsearch.elasticsearch.model.Product;
import com.elasticsearch.elasticsearch.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public String create(@RequestBody Product product) throws IOException {
        return service.save(product);
    }

    @GetMapping("/{id}")
    public Optional<Product> getById(@PathVariable String id) throws IOException {
        return service.findById(id);
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam String name) throws IOException {
        return service.searchByName(name);
    }

    @GetMapping("/search-all")
    public Iterable<Product> searchAll() throws IOException {
        return service.searchAll();
    }
}
