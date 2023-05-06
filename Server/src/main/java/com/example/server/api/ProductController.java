package com.example.server.api;

import com.example.server.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private static final String BASE_URL = "http://localhost:8082/api";

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/products"))
                .GET()
                .build();

        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> products = objectMapper.readValue(response.body(), new TypeReference<List<Product>>(){});

        return ResponseEntity.ok(products);
    }
}

