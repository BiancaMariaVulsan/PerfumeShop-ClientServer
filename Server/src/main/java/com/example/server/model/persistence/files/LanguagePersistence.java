package com.example.server.model.persistence.files;

import com.example.server.model.Language;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LanguagePersistence {
    public LanguagePersistence() {}

    public Language read(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Language language = objectMapper.readValue(new File(fileName), Language.class);

        System.out.println("First name column: " + language.getFirstNameColumn());
        System.out.println("Last name column: " + language.getLastNameColumn());
        System.out.println("Role column: " + language.getRoleColumn());
        System.out.println("Add button: " + language.getAddButton());
        System.out.println("Delete button: " + language.getDeleteButton());
        System.out.println("Edit button: " + language.getEditButton());
        System.out.println("Filter button: " + language.getFilterButton());
        System.out.println("Role choices: " + language.getRoleChoice());

        return language;
    }
}
