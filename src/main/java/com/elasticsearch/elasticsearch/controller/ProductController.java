package com.elasticsearch.elasticsearch.controller;

import com.elasticsearch.elasticsearch.model.Product;
import com.elasticsearch.elasticsearch.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.save(product);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable String id) {
        return service.findById(id).orElse(null);
    }

    @GetMapping("/search")
    public Iterable<Product> search(@RequestParam String name) {
        return service.searchByName(name);
    }

    @GetMapping("/get-all")
    public Iterable<Product> getAll() {
        return service.findAll();
    }
}
