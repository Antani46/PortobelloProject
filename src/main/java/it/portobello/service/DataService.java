package it.portobello.service;

import it.portobello.iterator.StoreIterator;
import it.portobello.model.CatalogItem;
import it.portobello.model.Category;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataService {

    // Metodo per salvare il catalogo su file
    public static void saveCatalogToFile(Category rootCategory, String filename) throws IOException {

        // Chiude automaticamente il file alla fine, evitando memory leak (Sicurezza).
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            writer.write("--- PORTOBELLO CATALOG EXPORT ---\n");
            writer.write("Totale Valore: " + rootCategory.getPrice() + "€\n\n");
            writer.write("LISTA OGGETTI:\n");

            StoreIterator iterator = new StoreIterator(rootCategory.getItems());

            while (iterator.hasNext()) {
                CatalogItem item = iterator.next();
                // Scriviamo riga per riga nel file
                String line = String.format("TYPE: %s | NAME: %s | PRICE: %.2f€",
                        item.getClass().getSimpleName(), // Ci dice se è Product o Category
                        item.getName(),
                        item.getPrice());

                writer.write(line + "\n");
            }

        }
    }
}