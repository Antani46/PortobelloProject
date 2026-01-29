package it.portobello.creation;

import it.portobello.exception.CatalogException; // Importa l'eccezione
import it.portobello.model.Category;
import it.portobello.model.Product;

public class CatalogFactory {

    // Aggiungi "throws CatalogException" qui
    public static Product createProduct(String name, String description, double price, String condition) throws CatalogException {
        return new Product(name, description, price, condition);
    }

    // E anche qui
    public static Category createCategory(String name) throws CatalogException {
        return new Category(name);
    }
}