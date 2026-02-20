package it.portobello.model;

import java.util.ArrayList;
import java.util.List;
import it.portobello.exception.CatalogException;

/**
 * Rappresenta un contenitore di elementi (Pattern Composite).
 * Può contenere sia oggetti Product che altre istanze di Category.
 */
public class Category implements CatalogItem {
    private final String name;
    private final List<CatalogItem> items; // final protegge il riferimento alla lista

    public Category(String name) throws CatalogException {
        if (name == null || name.trim().isEmpty()) {
            throw new CatalogException("Category name cannot be empty");
        }
        this.name = name;
        this.items = new ArrayList<>();
    }
    //Metodo per aggiungere l'oggetto
    public void addItem(CatalogItem item) {
        if (item != null) {
            this.items.add(item);
        }
    }
    //Metodo per rimuovere l'oggetto
    public void removeItem(CatalogItem item) {
        this.items.remove(item);
    }

    @Override
    public double getPrice() {
        // Calcolo ricorsivo del prezzo totale degli elementi contenuti
        double total = 0;
        for (CatalogItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    @Override
    public String getName() { return this.name; }

    @Override
    public void printDetails() {
        System.out.println("Wait... opening category: [" + name + "]");
        for (CatalogItem item : items) {
            item.printDetails();
        }
    }

    public List<CatalogItem> getItems(){
        return new ArrayList<>(this.items);
    }
}