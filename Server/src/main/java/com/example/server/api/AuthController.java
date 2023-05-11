package com.example.server.api;

import com.example.server.mediator.Mediator;
import com.example.server.mediator.ServiceMediatorImpl;
import com.example.server.model.LoginPerson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final Mediator mediator = new ServiceMediatorImpl();

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginPerson loginRequest) {
        String response = (String) mediator.notify("personService", loginRequest);
        return ResponseEntity.ok(response);
    }
}
