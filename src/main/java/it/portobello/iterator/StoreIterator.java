package it.portobello.iterator;

import it.portobello.model.CatalogItem;
import it.portobello.model.Category;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class StoreIterator implements Iterator<CatalogItem> {
    // Usiamo uno Stack (Pila) per gestire la navigazione dentro le cartelle annidate
    private Stack<Iterator<CatalogItem>> stack = new Stack<>();

    public StoreIterator(List<CatalogItem> items) {
        // All'inizio, mettiamo l'iteratore della lista principale nello stack
        this.stack.push(items.iterator());
    }

    @Override
    public boolean hasNext() {
        // Se lo stack è vuoto, abbiamo finito tutto
        if (stack.isEmpty()) {
            return false;
        } else {
            // Prendiamo l'iteratore corrente (quello in cima alla pila)
            Iterator<CatalogItem> iterator = stack.peek();

            if (!iterator.hasNext()) {
                // Se questo iteratore è finito (cartella vuota), lo togliamo
                stack.pop();
                // E controlliamo ricorsivamente se c'è altro sotto
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

            // IL TRUCCO DEL COMPOSITE:
            // Se l'elemento che ho appena trovato è una Categoria (un contenitore),
            // apro il contenitore e metto il suo iteratore in cima alla pila!
            if (component instanceof Category) {
                Category category = (Category) component;
                stack.push(category.getItems().iterator());
            }

            return component;
        } else {
            return null;
        }
    }
}