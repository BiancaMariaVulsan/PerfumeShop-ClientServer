package com.example.server.filesService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.mediator.requests.language.GetLanguageRequest;
import com.example.server.mediator.responses.language.GetLanguageResponse;
import com.example.server.model.Language;
import com.example.server.model.persistence.files.LanguagePersistence;

import java.util.Arrays;

public class GetLanguageHandler implements Handler {
    private Language english;
    private Language romanian;
    private Language german;
    private final LanguagePersistence languagePersistence;

    public GetLanguageHandler() {
        languagePersistence = new LanguagePersistence();
        read();
    }

    @Override
    public Response onMessage(Request message) {
        GetLanguageRequest languageRequest = (GetLanguageRequest) message;
        String language = languageRequest.getLanguage();
        GetLanguageResponse languageResponse = new GetLanguageResponse();
        if(language.equals("English")) {
            languageResponse.setLanguage(english);
        } else if(language.equals("Romanian")) {
            languageResponse.setLanguage(romanian);
        } else {
            languageResponse.setLanguage(german);
        }
        return languageResponse;
    }

    private void read() {
        try {
            english = languagePersistence.read("D:\\FACULTATE\\ANIII\\SEM2\\SD\\lab\\Project_Client_Server\\PerfumeShop-ClientServer\\Server\\EnglishLanguage.json");
            romanian = languagePersistence.read("D:\\FACULTATE\\ANIII\\SEM2\\SD\\lab\\Project_Client_Server\\PerfumeShop-ClientServer\\Server\\RomanianLanguage.json");
            german = languagePersistence.read("D:\\FACULTATE\\ANIII\\SEM2\\SD\\lab\\Project_Client_Server\\PerfumeShop-ClientServer\\Server\\GermanLanguage.json");
        } catch (Exception e) {
            System.out.println("Exception while reading: " + e.getMessage() + " at " + Arrays.toString(e.getStackTrace()));
        }
    }
}
