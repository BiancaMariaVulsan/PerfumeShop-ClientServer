package com.example.perfumeshop.requests;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;

public class LoginRequest {
    private static final String BASE_URL = "http://localhost:8080/api";

    public String login(String username, String password) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String jsonBody = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
