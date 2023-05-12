package com.example.server.mediator;

import com.example.server.mediator.requests.Request;
import com.example.server.mediator.responses.Response;

public interface Handler {
    public Response onMessage(Request message);
}
