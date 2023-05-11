package com.example.perfumeshop.requests;

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
import java.nio.charset.StandardCharsets;
import java.util.List;

public class PersonRequest {
    private static final String BASE_URL = "http://localhost:8080/api";

    public Integer getEmployeeShop(String username) throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/get_shop?username=" + username))
                .GET()
                .build();

        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        ObjectMapper objectMapper = new ObjectMapper();
        Integer shopId = objectMapper.readValue(response.body(), new TypeReference<>(){});

        return shopId;
    }

}
