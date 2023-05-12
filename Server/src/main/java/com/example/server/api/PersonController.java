package com.example.server.api;

import com.example.server.mediator.IMediator;
import com.example.server.mediator.Mediator;
import com.example.server.mediator.requests.GetEmployeeShopRequest;
import com.example.server.mediator.requests.GetPersonsRequest;
import com.example.server.mediator.responses.GetEmployeeShopResponse;
import com.example.server.mediator.responses.GetPersonsResponse;
import com.example.server.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {
    private final IMediator mediator = new Mediator();

    @GetMapping("/get_shop")
    public ResponseEntity<Integer> getShop(@RequestParam String username) {
        GetEmployeeShopRequest request = new GetEmployeeShopRequest();
        request.setUsername(username);
        GetEmployeeShopResponse response = (GetEmployeeShopResponse) mediator.send(request);

        return ResponseEntity.ok(response.getShopId());
    }

    @GetMapping("/get_persons")
    public ResponseEntity<List<Person>> getPersons() {
        GetPersonsRequest request = new GetPersonsRequest();
        GetPersonsResponse response = (GetPersonsResponse) mediator.send(request);

        return ResponseEntity.ok(response.getPersons());
    }
}
