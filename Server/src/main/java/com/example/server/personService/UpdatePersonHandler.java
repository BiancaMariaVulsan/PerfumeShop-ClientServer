package com.example.server.personService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.mediator.requests.persons.UpdatePersonRequest;
import com.example.server.mediator.responses.products.UpdatePersonResponse;
import com.example.server.model.persistence.PersonPersistence;
import com.example.server.personService.notifications.EmailService;
import com.example.server.personService.notifications.WhatsAppService;

public class UpdatePersonHandler implements Handler {
    private final PersonPersistence personPersistence;

    public UpdatePersonHandler() {
        this.personPersistence = new PersonPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        UpdatePersonRequest request = (UpdatePersonRequest) message;
        personPersistence.update(request.getPerson());
        try {
            EmailService.sendMessage("biavulsan@gmail.com",request.getPerson().getUsername(),request.getPerson().getPassword());
            WhatsAppService.sendMessage(request.getPerson().getUsername(),request.getPerson().getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UpdatePersonResponse("Person updated successfully");
    }
}
