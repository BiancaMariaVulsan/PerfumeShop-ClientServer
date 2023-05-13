package com.example.server;

import com.example.server.filesService.GetLanguageHandler;
import com.example.server.mediator.Mediator;
import com.example.server.mediator.requests.language.GetLanguageRequest;
import com.example.server.mediator.requests.persons.GetEmployeeShopRequest;
import com.example.server.mediator.requests.persons.GetPersonsRequest;
import com.example.server.mediator.requests.persons.LoginPersonRequest;
import com.example.server.mediator.requests.products.GetAllProductsRequest;
import com.example.server.mediator.requests.products.GetShopProductsRequest;
import com.example.server.mediator.responses.language.GetLanguageResponse;
import com.example.server.personService.GetEmployeeShopHandler;
import com.example.server.personService.GetPersonsHandler;
import com.example.server.personService.LoginPersonHandler;
import com.example.server.productService.FilterProductsHandler;
import com.example.server.productService.GetAllProductsHandler;
import com.example.server.productService.GetShopProductsHandler;
import com.example.server.mediator.requests.products.FilterProductsRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ServerApplication {

    public static void main(String[] args) {
        Mediator mediator = new Mediator();

        mediator.registerHandler(GetEmployeeShopRequest.class, GetEmployeeShopHandler.class);
        mediator.registerHandler(GetPersonsRequest.class, GetPersonsHandler.class);
        mediator.registerHandler(LoginPersonRequest.class, LoginPersonHandler.class);

        mediator.registerHandler(FilterProductsRequest.class, FilterProductsHandler.class);
        mediator.registerHandler(GetAllProductsRequest.class, GetAllProductsHandler.class);
        mediator.registerHandler(GetShopProductsRequest.class,GetShopProductsHandler.class);

        mediator.registerHandler(GetLanguageRequest.class, GetLanguageHandler.class);

        SpringApplication.run(ServerApplication.class, args);
    }

}
