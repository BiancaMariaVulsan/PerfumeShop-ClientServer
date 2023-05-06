package com.example.authservice.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Retrieve the username and password from the request body
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        // Return the token to the client
        return ResponseEntity.ok("Hello, " + username + "!");
    }
}
