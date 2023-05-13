package com.example.server.mediator.responses.language;

import com.example.server.mediator.Response;
import com.example.server.model.Language;

public class GetLanguageResponse implements Response {
    Language language;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
