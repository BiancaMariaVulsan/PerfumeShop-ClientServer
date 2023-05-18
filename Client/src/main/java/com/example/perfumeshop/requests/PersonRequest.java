package com.example.perfumeshop.requests;

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

}
