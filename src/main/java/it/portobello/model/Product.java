package it.portobello.model;

import it.portobello.exception.CatalogException; // <--- Importante!

/**
 * Rappresenta un singolo articolo nel catalogo.
 * Implementa la validazione degli attributi nel costruttore per garantire l'integrità dei dati.
 */
public class Product implements CatalogItem {
    private final String name;
    private final String description;
    private final double price;
    private final String condition;

    /**
     * Costruttore con validazione dei parametri.
     * @throws CatalogException se i dati in ingresso non sono validi (es. prezzo negativo).
     */
    public Product(String name, String description, double price, String condition) throws CatalogException {
        // VALIDAZIONE
        if (name == null || name.trim().isEmpty()) {
            throw new CatalogException("Error: Product name cannot be empty.");
        }
        if (price < 0) {
            throw new CatalogException("Error: Price cannot be negative (" + price + ").");
        }

        this.name = name;
        this.description = description;
        this.price = price;
        this.condition = condition;
    }
    @Override
    public double getPrice() { return this.price; }
    @Override
    public String getName() { return this.name; }
    @Override
    public void printDetails() {
        System.out.println("  - Product: " + name + " | Price: " + price + "€ | Cond: " + condition);
    }
}