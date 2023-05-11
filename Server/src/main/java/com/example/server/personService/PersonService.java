package com.example.server.personService;

import com.example.server.mediator.Service;
import com.example.server.model.LoginPerson;
import com.example.server.personService.logic.LogInLogic;

public class PersonService implements Service {
    @Override
    public Object onMessage(Object message) {
        String username = ((LoginPerson) message).getUsername();
        String password = ((LoginPerson) message).getPassword();
        LogInLogic logInLogic = new LogInLogic(username, password);
        return logInLogic.signIn();
    }
}
