package com.example.server;

import com.example.server.exportService.ExportService;
import com.example.server.mediator.Mediator;
import com.example.server.mediator.ServiceMediatorImpl;
import com.example.server.personService.PersonService;
import com.example.server.productService.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ServerApplication {

    public static void main(String[] args) {
        ServiceMediatorImpl mediator = new ServiceMediatorImpl();
        ProductService productService = new ProductService();
        PersonService personService = new PersonService();
        ExportService exportService = new ExportService();
        mediator.registerService("productService", productService);
        mediator.registerService("personService", personService);
        mediator.registerService("exportService", exportService);
        SpringApplication.run(ServerApplication.class, args);
    }

}
