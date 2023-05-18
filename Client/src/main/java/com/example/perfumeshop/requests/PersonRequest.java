package com.example.perfumeshop.requests;

import com.example.perfumeshop.model.AddProductRequest;
import com.example.perfumeshop.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class PersonRequest {
    private static final String BASE_URL = "http://localhost:8080/api";
    private final HttpClient client = HttpClient.newHttpClient();

    public Integer getEmployeeShop(String username) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/get_shop?username=" + username))
                .GET()
                .build();

        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.body(), new TypeReference<>(){});
    }

    public List<Person> getPersons() throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/get_persons"))
                .GET()
                .build();

        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.body(), new TypeReference<>(){});
    }

    public List<Person> filterPersons(String role) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/filter_users?role=" + role))
                .GET()
                .build();

        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.body(), new TypeReference<>(){});
    }

    public String deletePerson(Person person) throws URISyntaxException, IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(person);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/delete_person"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String addPerson(Person person) throws URISyntaxException, IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(person);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/add_person"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String updatePerson(Person person) throws URISyntaxException, IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(person);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/update_person"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
