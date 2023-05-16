package com.example.server.filesService.bridge;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class XmlFormat implements IFormat{
    @Override
    public void saveToFile(List<?> list, String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(list.get(0).getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            FileWriter writer = new FileWriter(fileName);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
            writer.write("<objects>\n");
            for (Object obj : list) {
                marshaller.marshal(obj, writer);
            }
            writer.write("</objects>\n");
            writer.flush();
            writer.close();
            System.out.println("Objects saved to XML file successfully.");
        } catch (IOException | JAXBException e) {
            System.out.println("An error occurred while saving objects to XML file: " + e.getMessage());
        }
    }
}
