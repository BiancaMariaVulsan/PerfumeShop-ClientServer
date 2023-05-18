package com.example.server.api;

import com.example.server.mediator.IMediator;
import com.example.server.mediator.Mediator;
import com.example.server.mediator.requests.persons.FilterPersonsRequest;
import com.example.server.mediator.requests.persons.GetEmployeeShopRequest;
import com.example.server.mediator.requests.persons.GetPersonsRequest;
import com.example.server.mediator.requests.persons.GetShopRequest;
import com.example.server.mediator.responses.persons.FilterPersonsResponse;
import com.example.server.mediator.responses.persons.GetEmployeeShopResponse;
import com.example.server.mediator.responses.persons.GetPersonsResponse;
import com.example.server.mediator.responses.persons.GetShopResponse;
import com.example.server.model.Person;
import com.example.server.model.Shop;
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

    @GetMapping("/shops")
    public ResponseEntity<List<Shop>> getShops() {
        GetShopRequest request = new GetShopRequest();
        GetShopResponse response = (GetShopResponse) mediator.send(request);

        return ResponseEntity.ok(response.getShops());
    }

    @GetMapping("/filter_users")
    public ResponseEntity<List<Person>> filterPersons(@RequestParam String role) {
        FilterPersonsRequest filterPersonsRequest = new FilterPersonsRequest(role);
        FilterPersonsResponse filterPersonsResponse = (FilterPersonsResponse) mediator.send(filterPersonsRequest);

        return ResponseEntity.ok(filterPersonsResponse.getFilteredPersons());
    }
}
