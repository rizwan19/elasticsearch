package com.elasticsearch.elasticsearch.service;

import com.elasticsearch.elasticsearch.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private static final String INDEX = "products";
    private final RestHighLevelClient client;
    private final ObjectMapper objectMapper;

    public ProductService(RestHighLevelClient client) {
        this.client = client;
        this.objectMapper = new ObjectMapper();
    }


    public String save(Product product) throws IOException {
        IndexRequest request = new IndexRequest(INDEX);
        String id = UUID.randomUUID().toString();
        request.id(id);
        product.setId(id);
        request.source(objectMapper.writeValueAsString(product), XContentType.JSON);
        client.index(request, RequestOptions.DEFAULT);
        return id;
    }

    public Optional<Product> findById(String id) throws IOException {
        GetRequest request = new GetRequest(INDEX, id);
        var response = client.get(request, RequestOptions.DEFAULT);
        if(response.isExists()){
            return Optional.ofNullable(objectMapper.readValue(response.getSourceAsString(), Product.class));
        }
        return Optional.empty();
    }

    public List<Product> searchByName(String name) throws IOException {
        SearchRequest request = new SearchRequest(INDEX);
        MatchQueryBuilder matchQuery = new MatchQueryBuilder("name", name);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(matchQuery);

        request.source(sourceBuilder);

        return getProducts(request);
    }

    public List<Product> searchAll() throws IOException {
        SearchRequest request = new SearchRequest(INDEX);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        sourceBuilder.size(1000);
        request.source(sourceBuilder);
        return getProducts(request);
    }

    private List<Product> getProducts(SearchRequest request) throws IOException {
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        List<Product> resultList = new ArrayList<>();

        for(SearchHit hit : response.getHits()){
            Product product = objectMapper.readValue(hit.getSourceAsString(), Product.class);
            resultList.add(product);
        }
        return resultList;
    }
}
