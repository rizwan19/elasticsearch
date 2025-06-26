package com.elasticsearch.elasticsearch.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private String id;
    private String name;
    private String description;
    private double price;
}
