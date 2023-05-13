package com.example.server.api;

import com.example.server.mediator.IMediator;
import com.example.server.mediator.Mediator;
import com.example.server.mediator.requests.language.GetLanguageRequest;
import com.example.server.mediator.requests.products.GetShopProductsRequest;
import com.example.server.mediator.responses.language.GetLanguageResponse;
import com.example.server.model.Language;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LanguageController {
    private final IMediator mediator = new Mediator();

    @GetMapping("/languages")
    public ResponseEntity<Language> getShop(@RequestParam String language) {
        GetLanguageRequest request = new GetLanguageRequest();
        request.setLanguage(language);
        GetLanguageResponse response = (GetLanguageResponse) mediator.send(request);
        return ResponseEntity.ok(response.getLanguage());
    }
}
