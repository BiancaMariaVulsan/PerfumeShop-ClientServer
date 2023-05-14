package com.example.perfumeshop.requests;

import com.example.perfumeshop.model.AddProductRequest;
import com.example.perfumeshop.model.Product;
import com.example.perfumeshop.model.ShopProduct;
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
    private final HttpClient client = HttpClient.newHttpClient();

    public List<Product> getAllProducts() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/products"))
                .GET()
                .build();

        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), new TypeReference<>(){});
    }

    public List<ShopProduct> getShopProducts(int shopId) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/shop_products?shopId=" + shopId))
                .GET()
                .build();

        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), new TypeReference<>(){});
    }

    public List<Product> getProductsAvailableInTheChain() throws URISyntaxException, IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/products/available"))
                .GET()
                .build();

        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), new TypeReference<>(){});
    }

    public List<Product> filterProducts(String name, String brand, boolean availability, float price) throws URISyntaxException, IOException, InterruptedException {
        String jsonBody = "{\"name\":\"" + name + "\"," +
                "\"brand\":\"" + brand + "\"," +
                "\"availability\":" + availability + "," +
                "\"price\":" + price + "}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/filter_products"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), new TypeReference<>(){});
    }

    public String addProduct(ShopProduct shopProduct, int shopId) throws URISyntaxException, IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(new AddProductRequest(shopProduct, shopId));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/add_product"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
