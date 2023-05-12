package com.example.server.filesService.logic;

import com.example.server.model.Language;
import com.example.server.model.persistence.files.LanguagePersistence;

import java.util.Arrays;

public class LanguageLogic {
    private final LanguagePersistence languagePersistence = new LanguagePersistence();
    private Language english;
    private Language romanian;
    private Language german;

    public LanguageLogic() {
        read();
    }

    public void read() {
        try {
            english = languagePersistence.read("D:\\FACULTATE\\ANIII\\SEM2\\SD\\lab\\Project_Client_Server\\PerfumeShop-ClientServer\\Server\\EnglishLanguage.json");
            romanian = languagePersistence.read("D:\\FACULTATE\\ANIII\\SEM2\\SD\\lab\\Project_Client_Server\\PerfumeShop-ClientServer\\Server\\RomanianLanguage.json");
            german = languagePersistence.read("D:\\FACULTATE\\ANIII\\SEM2\\SD\\lab\\Project_Client_Server\\PerfumeShop-ClientServer\\Server\\GermanLanguage.json");
        } catch (Exception e) {
            System.out.println("Exception while reading: " + e.getMessage() + " at " + Arrays.toString(e.getStackTrace()));
        }
    }

    public Language getLanguage(String language) {
        if(language.equals("English")) {
            return english;
        } else if(language.equals("Romanian")) {
            return romanian;
        } else {
            return german;
        }
    }
}
