package it.portobello.service;

import it.portobello.iterator.StoreIterator;
import it.portobello.model.CatalogItem;
import it.portobello.model.Category;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataService {

    // Salva su File
    public static void saveCatalogToFile(Category rootCategory, String filename) throws IOException {

        // Usa "try-with-resources"
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            writer.write("--- PORTOBELLO CATALOG EXPORT ---\n");
            writer.write("Totale Valore: " + rootCategory.getPrice() + "€\n\n");
            writer.write("LISTA OGGETTI:\n");

            // Usa Iterator
            StoreIterator iterator = new StoreIterator(rootCategory.getItems());

            while (iterator.hasNext()) {
                CatalogItem item = iterator.next();
                // Scrive riga per irga
                String line = String.format("TYPE: %s | NAME: %s | PRICE: %.2f€",
                        item.getClass().getSimpleName(), // se è Product o Category
                        item.getName(),
                        item.getPrice());

                writer.write(line + "\n");
            }

        }
    }
}