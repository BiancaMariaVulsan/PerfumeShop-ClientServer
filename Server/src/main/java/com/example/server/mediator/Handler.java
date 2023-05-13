package com.example.server.mediator;

public interface Handler {
    public Response onMessage(Request message);
}
