package com.example.perfumeshop.requests;

import com.example.perfumeshop.model.Language;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LanguageRequest {
    private static final String BASE_URL = "http://localhost:8080/api";

    public Language getLanguage(String language) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/languages?language=" + language))
                .GET()
                .build();
        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        ObjectMapper objectMapper = new ObjectMapper();
        Language lng = objectMapper.readValue(response.body(), new TypeReference<>(){});
        return lng;
    }
}
