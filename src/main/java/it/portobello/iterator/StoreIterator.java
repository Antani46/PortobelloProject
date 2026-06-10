package it.portobello.iterator;

import it.portobello.model.CatalogItem;
import it.portobello.model.Category;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class StoreIterator implements Iterator<CatalogItem> {
    // Stack
    private Stack<Iterator<CatalogItem>> stack = new Stack<>();

    public StoreIterator(List<CatalogItem> items) {
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