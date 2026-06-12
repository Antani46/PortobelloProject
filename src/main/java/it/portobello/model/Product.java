package it.portobello.model;

import it.portobello.exception.CatalogException;

public class Product implements CatalogItem {
    private final String name;
    private final String description;
    private final double price;
    private final String condition;

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
        System.out.println("  - Product: " + name + " " + description + " | Price: " + price + "€ | Cond: " + condition);
    }
}