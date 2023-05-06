package com.example.perfumeshop.requests;

import com.example.perfumeshop.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ProductRequest {
    private static final String BASE_URL = "http://localhost:8080/api";

    public List<Product> getAllProducts() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/products"))
                .GET()
                .build();

        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> products = objectMapper.readValue(response.body(), new TypeReference<List<Product>>(){});
        return products;
    }

    // todo: handle this request
    public boolean isAvailableInTheChain(int productId) throws URISyntaxException, IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/products/available/" + productId))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return Boolean.getBoolean(response.body());
    }
}
