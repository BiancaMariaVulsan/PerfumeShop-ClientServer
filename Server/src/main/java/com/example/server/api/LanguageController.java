package com.example.server.api;

import com.example.server.mediator.Mediator;
import com.example.server.mediator.ServiceMediatorImpl;
import com.example.server.model.Language;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LanguageController {
    private final Mediator mediator = new ServiceMediatorImpl();

    @GetMapping("/languages")
    public ResponseEntity<Language> getShop(@RequestParam String language) {
        Language lng = (Language) mediator.notify("filesService", language);
        return ResponseEntity.ok(lng);
    }
}