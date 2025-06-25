package com.elasticsearch.elasticsearch.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
}
