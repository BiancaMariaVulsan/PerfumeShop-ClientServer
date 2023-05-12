package com.example.server.personService;

import com.example.server.mediator.Service;
import com.example.server.model.LoginPerson;
import com.example.server.personService.logic.LogInLogic;
import com.example.server.personService.logic.PersonLogic;

public class PersonService implements Service {
    private final PersonLogic personLogic = new PersonLogic();
    @Override
    public Object onMessage(Object message) {
        if(message instanceof  LoginPerson) {
            String username = ((LoginPerson) message).getUsername();
            String password = ((LoginPerson) message).getPassword();
            LogInLogic logInLogic = new LogInLogic(username, password);
            return logInLogic.signIn();
        } else if (message instanceof String){
            return personLogic.getEmployeeShop((String) message);
        }
        return null;
    }
}
