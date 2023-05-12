package com.example.server.mediator;

import com.example.server.mediator.requests.Request;
import com.example.server.mediator.responses.Response;

public interface IMediator {
    public Response send(Request request);
}
