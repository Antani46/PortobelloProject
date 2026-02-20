package it.portobello.iterator;

import it.portobello.model.CatalogItem;
import it.portobello.model.Category;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Iteratore personalizzato per la scansione del catalogo (Pattern Iterator).
 * Gestisce la navigazione in profondità della struttura gerarchica utilizzando uno Stack.
 */
public class StoreIterator implements Iterator<CatalogItem> {
    private final Stack<Iterator<CatalogItem>> stack = new Stack<>();

    public StoreIterator(List<CatalogItem> items) {
        // Inizializza lo stack con l'iteratore del livello radice
        this.stack.push(items.iterator());
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            return false;
        } else {
            Iterator<CatalogItem> iterator = stack.peek();

            if (!iterator.hasNext()) {
                stack.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }

    @Override
    public CatalogItem next() {
        if (hasNext()) {
            Iterator<CatalogItem> iterator = stack.peek();
            CatalogItem component = iterator.next();

            // Se l'elemento corrente è una categoria, aggiungiamo il suo iteratore allo stack
            if (component instanceof Category category) { // 'category' è la variabile di pattern
                stack.push(category.getItems().iterator());
            }

            return component;
        } else {
            return null;
        }
    }
}