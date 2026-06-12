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

            Product p1 = CatalogFactory.createProduct("Lampada Vintage", "Anni 60 space-age", 120.0, "Good");
            Product p2 = CatalogFactory.createProduct("Sedia Legno", "Seduta in paglia fatta a mano", 45.50, "Worn");
            Product p3 = CatalogFactory.createProduct("Specchio Vintage", "Cornice dorata lavorata", 210.0, "Good");
            Product p4 = CatalogFactory.createProduct("Tavolino Caffè", "Ripiano in vetro e base marmo", 85.00, "New");

            Product ph1 = CatalogFactory.createProduct("iPhone 4", "Da collezione, scatola originale", 150.0, "New");
            Product ph2 = CatalogFactory.createProduct("Nokia 3310", "Indistruttibile, batteria nuova", 35.00, "Good");
            Product ph3 = CatalogFactory.createProduct("BlackBerry", "Tastiera QWERTY perfetta", 60.00, "Worn");

            Product pc1 = CatalogFactory.createProduct("MacBook Pro 2012", "Ram potenziata, ottimo per studio", 280.0, "Good");
            Product pc2 = CatalogFactory.createProduct("Commodore 64", "Funzionante, con alimentatore originale", 190.0, "Good");

            Product b1 = CatalogFactory.createProduct("Il Signore degli Anelli", "Edizione illustrata rigida", 40.00, "New");
            Product b2 = CatalogFactory.createProduct("Dune - Prima Edizione", "Copertina morbida, rara", 75.00, "Good");
            Product v1 = CatalogFactory.createProduct("The Dark Side of the Moon", "Vinile Pink Floyd 1973 original", 110.0, "Good");

            // Creiamo le categorie
            Category rootCategory = CatalogFactory.createCategory("Negozio Portobello");
            Category arredamento = CatalogFactory.createCategory("Arredamento & Oggettistica");
            Category elettronica = CatalogFactory.createCategory("Elettronica Vintage");
            Category cultura = CatalogFactory.createCategory("Libri & Vinili");

            Category sottoSmartphone = CatalogFactory.createCategory("Telefonia & Mobile");
            Category sottoComputer = CatalogFactory.createCategory("Retrocomputing & PC");

            // Composizione
            arredamento.addItem(p1);
            arredamento.addItem(p2);
            elettronica.addItem(p3);
            arredamento.addItem(p4);

            sottoSmartphone.addItem(ph1);
            sottoSmartphone.addItem(ph2);
            sottoSmartphone.addItem(ph3);

            sottoComputer.addItem(pc1);
            sottoComputer.addItem(pc2);

            elettronica.addItem(sottoSmartphone);
            elettronica.addItem(sottoComputer);

            cultura.addItem(b1);
            cultura.addItem(b2);
            cultura.addItem(v1);

            rootCategory.addItem(arredamento);
            rootCategory.addItem(elettronica);
            rootCategory.addItem(cultura);

            logger.info("Totale valore magazzino: {}€", rootCategory.getPrice());
            rootCategory.printDetails();

            logger.info("\n--- STAMPA CON ITERATOR");

            StoreIterator iterator = new StoreIterator(rootCategory.getItems());

            while (iterator.hasNext()) {
                CatalogItem item = iterator.next();
                logger.info("-> {} | Prezzo: {}", item.getName(), item.getPrice());
            }

            logger.info("\n--- SALVATAGGIO SU FILE ---");
            it.portobello.service.DataService.saveCatalogToFile(rootCategory, "export_catalogo.txt");
            logger.info("Salvataggio completato con successo nel file: export_catalogo.txt");

        } catch (CatalogException e) {
            logger.error("ERRORE CATALOGO: {}", e.getMessage());

        } catch (Exception e) {
            logger.error("ERRORE DI SISTEMA: {}", e.getMessage());
        }
    }
}