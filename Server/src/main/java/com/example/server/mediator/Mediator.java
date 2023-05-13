package com.example.server.mediator;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Mediator implements IMediator {
    private static Map<String, Class> services;

    public Mediator() {
        if (services == null) {
            services = new HashMap<>();
        }
    }

    public void registerHandler(Class requestClass, Class handlerClass) {
        services.put(requestClass.getSimpleName(), handlerClass);
    }

    public Response send(Request request) {
        String requestName = request.getClass().getSimpleName();
        if (services.containsKey(requestName)) {
            try {
                Handler handler = (Handler)services.get(requestName).getDeclaredConstructor().newInstance();
                Response response = handler.onMessage(request);
                return response;
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Handle error: service not found
        }
        return null;
    }
}
