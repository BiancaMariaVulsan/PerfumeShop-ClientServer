package com.example.server.mediator;

public interface Mediator {
    public Object notify(String serviceName, Object message);
}
