package com.example.server;

import com.example.server.filesService.GetLanguageHandler;
import com.example.server.filesService.SaveProductsHandler;
import com.example.server.filesService.SaveShopProductsHandler;
import com.example.server.mediator.Mediator;
import com.example.server.mediator.requests.language.GetLanguageRequest;
import com.example.server.mediator.requests.persons.*;
import com.example.server.mediator.requests.products.*;
import com.example.server.personService.*;
import com.example.server.productService.*;
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
        mediator.registerHandler(FilterPersonsRequest.class, FilterPersonsHandler.class);
        mediator.registerHandler(AddPersonRequest.class, AddPersonHandler.class);
        mediator.registerHandler(UpdatePersonRequest.class, UpdatePersonHandler.class);
        mediator.registerHandler(DeletePersonRequest.class, DeletePersonHandler.class);

        mediator.registerHandler(FilterProductsRequest.class, FilterProductsHandler.class);
        mediator.registerHandler(FilterShopProductRequest.class, FilterShopProductHandler.class);
        mediator.registerHandler(GetAllProductsRequest.class, GetAllProductsHandler.class);
        mediator.registerHandler(GetShopProductsRequest.class,GetShopProductsHandler.class);
        mediator.registerHandler(GetProductsAvailableInTheChainRequest.class, GetProductsAvailableInTheChainHandler.class);
        mediator.registerHandler(AddProductRequest.class, AddProductHandler.class);
        mediator.registerHandler(DeleteProductRequest.class, DeleteProductHandler.class);
        mediator.registerHandler(UpdateProductRequest.class, UpdateProductHandler.class);

        mediator.registerHandler(GetLanguageRequest.class, GetLanguageHandler.class);

        mediator.registerHandler(SaveProductsRequest.class, SaveProductsHandler.class);
        mediator.registerHandler(SaveShopProductsRequest.class, SaveShopProductsHandler.class);

        mediator.registerHandler(GetShopRequest.class, GetShopHandler.class);

        SpringApplication.run(ServerApplication.class, args);
    }

}
