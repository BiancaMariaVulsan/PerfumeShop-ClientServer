package com.example.server.mediator;

import java.util.HashMap;
import java.util.Map;

public class ServiceMediatorImpl implements Mediator{
    private static final Map<String, Service> services = new HashMap<>();

    public void registerService(String serviceName, Service service) {
        services.put(serviceName, service);
    }

    public Object notify(String serviceName, Object message) {
        if (services.containsKey(serviceName)) {
            return services.get(serviceName).onMessage(message);
        } else {
            // Handle error: service not found
        }
        return null;
    }
}
