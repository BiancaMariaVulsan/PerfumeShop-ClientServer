package com.example.server.model.persistence.files;

import com.example.server.model.Product;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class XmlPersistence {
    private final List<Product> products;
    private final String fileName;

    public XmlPersistence(List<Product> products, String fileName) {
        this.products = products;
        this.fileName = fileName;
    }

    public boolean save() {
        try {
            JAXBContext context = JAXBContext.newInstance(Product.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            FileWriter writer = new FileWriter(fileName);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
            writer.write("<products>\n");
            for (Product product : products) {
                marshaller.marshal(product, writer);
            }
            writer.write("</products>\n");
            writer.flush();
            writer.close();
            System.out.println("Products saved to XML file successfully.");
            return true;
        } catch (IOException | JAXBException e) {
            System.out.println("An error occurred while saving products to XML file: " + e.getMessage());
            return false;
        }
    }
}
