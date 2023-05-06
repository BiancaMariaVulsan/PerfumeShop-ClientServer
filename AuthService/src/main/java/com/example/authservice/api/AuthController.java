package com.example.authservice.api;

import com.example.authservice.logic.LogInLogic;
import com.example.authservice.model.LoginRequest;
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
        LogInLogic logInLogic = new LogInLogic(username, password);
        if(logInLogic.signIn()) {
            return ResponseEntity.ok("Hello!");
        } else {
            return ResponseEntity.status(401).body("Wrong username or password!");
        }
    }
}
