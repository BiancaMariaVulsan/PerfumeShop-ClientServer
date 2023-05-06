package com.example.perfumeshop.requests;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;

public class LoginRequest {

    public void login(String username, String password) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String jsonBody = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String token = response.body();
        if (response.statusCode() == 200) {
            System.out.println("Login successful");
            System.out.println("Token: " + token);
        } else {
            System.out.println("Login failed");
            System.out.println("Token: " + token);
        }
    }
}
