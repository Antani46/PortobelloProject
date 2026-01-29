package it.portobello.service;

import it.portobello.iterator.StoreIterator;
import it.portobello.model.CatalogItem;
import it.portobello.model.Category;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataService {

    // Metodo per salvare il catalogo su file (Java I/O - 3 Punti)
    public static void saveCatalogToFile(Category rootCategory, String filename) throws IOException {

        // Usiamo "try-with-resources" (il try con parentesi tonda).
        // Chiude automaticamente il file alla fine, evitando memory leak (Sicurezza).
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            writer.write("--- PORTOBELLO CATALOG EXPORT ---\n");
            writer.write("Totale Valore: " + rootCategory.getPrice() + "€\n\n");
            writer.write("LISTA OGGETTI:\n");

            // USIAMO IL TUO ITERATOR!
            // Grazie all'iteratore, scrivere su file è facilissimo perché abbiamo una lista
            // piatta.
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