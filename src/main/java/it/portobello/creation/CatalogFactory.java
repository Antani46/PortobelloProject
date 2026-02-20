package it.portobello.creation;

import it.portobello.exception.CatalogException; // Importa l'eccezione
import it.portobello.model.Category;
import it.portobello.model.Product;

/**
 * Eccezione personalizzata di tipo Checked.
 * Utilizzata per segnalare violazioni dei vincoli di business nel catalogo.
 * L'estensione di 'Exception' obbliga il chiamante a gestire esplicitamente l'errore.
 */
public class CatalogFactory {

    public static Product createProduct(String name, String description, double price, String condition) throws CatalogException {
        return new Product(name, description, price, condition);
    }

    public static Category createCategory(String name) throws CatalogException {
        return new Category(name);
    }
}