package com.example.server.filesService;

import com.example.server.filesService.logic.LanguageLogic;
import com.example.server.mediator.Handler;
import com.example.server.mediator.requests.Request;
import com.example.server.mediator.responses.Response;

public class FilesService implements Handler {
    @Override
    public Response onMessage(Request message) {
        return null;
    }
//    LanguageLogic languageLogic = new LanguageLogic();
//
//    @Override
//    public Object onMessage(Object message) {
//        if (message instanceof String) {
//            return languageLogic.getLanguage((String) message);
//        }
//        return null;
//    }
}
