package com.example.server.filesService;

import com.example.server.filesService.logic.LanguageLogic;
import com.example.server.mediator.Service;

public class FilesService implements Service {
    LanguageLogic languageLogic = new LanguageLogic();

    @Override
    public Object onMessage(Object message) {
        if (message instanceof String) {
            return languageLogic.getLanguage((String) message);
        }
        return null;
    }
}
