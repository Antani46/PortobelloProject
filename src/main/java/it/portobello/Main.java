package it.portobello;

import it.portobello.creation.CatalogFactory;
import it.portobello.exception.CatalogException;
import it.portobello.iterator.StoreIterator;
import it.portobello.model.CatalogItem;
import it.portobello.model.Category;
import it.portobello.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("--- BENVENUTO A PORTOBELLO MANAGER ---");

        try {

            // 1. Creiamo i prodotti tramite Factory
            Product p1 = CatalogFactory.createProduct("Lampada Vintage", "Anni 60", 50.0, "Good");
            Product p2 = CatalogFactory.createProduct("Sedia Legno", "Fatta a mano", 20.0, "Worn");
            Product p3 = CatalogFactory.createProduct("iPhone 4", "Da collezione", 100.0, "New");

            // 2. Creiamo le categorie
            Category rootCategory = CatalogFactory.createCategory("Negozio Portobello");
            Category arredamento = CatalogFactory.createCategory("Arredamento");
            Category elettronica = CatalogFactory.createCategory("Elettronica");

            // 3. Composizione
            arredamento.addItem(p1);
            arredamento.addItem(p2);
            elettronica.addItem(p3);
            rootCategory.addItem(arredamento);
            rootCategory.addItem(elettronica);

            // 4. Output
            logger.info("Totale valore magazzino: {}€", rootCategory.getPrice());
            rootCategory.printDetails();

            logger.info("\n--- STAMPA CON ITERATOR (Lista Piatta)");

            StoreIterator iterator = new StoreIterator(rootCategory.getItems());

            while (iterator.hasNext()) {
                CatalogItem item = iterator.next();
                logger.info("-> Trovato; {} | Prezzo: {}", item.getName(), item.getPrice());
            }

            logger.info("\n--- SALVATAGGIO SU FILE ---");
            // Crea file "export_catalogo.txt"
            it.portobello.service.DataService.saveCatalogToFile(rootCategory, "export_catalogo.txt");
            logger.info("Salvataggio completato con successo nel file: export_catalogo.txt");

        } catch (CatalogException e) {
            // QUI GESTIAMO L'ERRORE SPECIFICO (Exception Shielding)

            logger.error("ERRORE CATALOGO: {}", e.getMessage());

        } catch (Exception e) {
            // QUI GESTIAMO IMPREVISTI GENERICI
            logger.error("ERRORE DI SISTEMA: {}", e.getMessage());
        }
    }
}