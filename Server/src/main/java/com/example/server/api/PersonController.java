package com.example.server.api;

import com.example.server.mediator.IMediator;
import com.example.server.mediator.Mediator;
import com.example.server.mediator.requests.persons.*;
import com.example.server.mediator.responses.persons.FilterPersonsResponse;
import com.example.server.mediator.responses.persons.GetEmployeeShopResponse;
import com.example.server.mediator.responses.persons.GetPersonsResponse;
import com.example.server.mediator.responses.persons.GetShopResponse;
import com.example.server.mediator.responses.products.AddPersonResponse;
import com.example.server.mediator.responses.products.DeletePersonResponse;
import com.example.server.mediator.responses.products.UpdatePersonResponse;
import com.example.server.model.Person;
import com.example.server.model.Shop;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/delete_person")
    public ResponseEntity<String> deletePerson(@RequestBody Person person) {
        DeletePersonRequest deletePersonRequest = new DeletePersonRequest(person);
        DeletePersonResponse response = (DeletePersonResponse) mediator.send(deletePersonRequest);

        return ResponseEntity.ok(response.getMessage());
    }

    @PostMapping("/add_person")
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        AddPersonRequest addPersonRequest = new AddPersonRequest(person);
        AddPersonResponse response = (AddPersonResponse) mediator.send(addPersonRequest);

        return ResponseEntity.ok(response.getMessage());
    }

    @PostMapping("/update_person")
    public ResponseEntity<String> updatePerson(@RequestBody Person person) {
        UpdatePersonRequest updatePersonRequest = new UpdatePersonRequest(person);
        UpdatePersonResponse response = (UpdatePersonResponse) mediator.send(updatePersonRequest);

        return ResponseEntity.ok(response.getMessage());
    }
}
