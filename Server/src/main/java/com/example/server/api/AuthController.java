package com.example.server.api;

import com.example.server.mediator.IMediator;
import com.example.server.mediator.Mediator;
import com.example.server.mediator.requests.LoginPersonRequest;
import com.example.server.mediator.requests.Request;
import com.example.server.mediator.responses.LoginResponse;
import com.example.server.model.LoginPerson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final IMediator mediator = new Mediator(); // TODO: make sure Mediator is singleton

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginPerson loginRequest) {
        LoginPersonRequest request = new LoginPersonRequest();
        request.setUsername(loginRequest.getUsername());
        request.setPassword(loginRequest.getPassword());
        LoginResponse response = (LoginResponse) mediator.send(request);

        if (response.getError() == null || response.getError().length() == 0) {
            return ResponseEntity.ok(response.getRole());
        }
        else {
            return ResponseEntity.ok(response.getError());
        }
    }
}
