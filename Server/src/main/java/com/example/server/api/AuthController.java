package com.example.server.api;

import com.example.server.model.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws IOException, InterruptedException {
        // Retrieve the username and password from the request body
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        HttpClient client = HttpClient.newHttpClient();
        String jsonBody = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/api/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Return the token to the client
        if(response.statusCode() == 401) {
            return ResponseEntity.status(401).body("Wrong username or password!");
        } else {
            return ResponseEntity.ok(response.body());
        }
    }
}
