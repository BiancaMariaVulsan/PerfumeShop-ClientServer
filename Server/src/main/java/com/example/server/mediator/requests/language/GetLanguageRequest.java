package com.example.server.mediator.requests.language;

import com.example.server.mediator.Request;

public class GetLanguageRequest implements Request {
    private String language;

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
}
