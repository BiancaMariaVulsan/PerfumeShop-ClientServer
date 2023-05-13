package com.example.server;

import com.example.server.mediator.Mediator;
import com.example.server.personService.requests.GetEmployeeShopRequest;
import com.example.server.personService.requests.GetPersonsRequest;
import com.example.server.personService.requests.LoginPersonRequest;
import com.example.server.personService.GetEmployeeShopHandler;
import com.example.server.personService.GetPersonsHandler;
import com.example.server.personService.LoginPersonHandler;
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

        SpringApplication.run(ServerApplication.class, args);
    }

}
