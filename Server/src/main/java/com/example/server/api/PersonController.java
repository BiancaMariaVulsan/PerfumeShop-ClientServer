package com.example.server.api;

import com.example.server.mediator.Mediator;
import com.example.server.mediator.ServiceMediatorImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PersonController {
    private final Mediator mediator = new ServiceMediatorImpl();

    @GetMapping("/get_shop")
    public ResponseEntity<Integer> getShop(@RequestParam String username) {
        Integer shop = (Integer) mediator.notify("personService", username);
        return ResponseEntity.ok(shop);
    }
}
