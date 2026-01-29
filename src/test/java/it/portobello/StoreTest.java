package it.portobello;

import it.portobello.creation.CatalogFactory;
import it.portobello.exception.CatalogException;
import it.portobello.model.Category;
import it.portobello.model.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StoreTest {

    @Test
    public void testCalculateTotal() throws CatalogException {
        // Test Calcolo Totale
        Category category = new Category("Test Category");
        Product p1 = new Product("P1", "Desc1", 10.0, "New");
        Product p2 = new Product("P2", "Desc2", 20.0, "Used");

        category.addItem(p1);
        category.addItem(p2);

        assertEquals(30.0, category.getPrice(), 0.001, "Il totale della categoria deve essere 30€");
    }

    @Test
    public void testNegativePriceException() {
        // Test Eccezione Prezzo Negativo
        Exception exception = assertThrows(CatalogException.class, () -> {
            new Product("BadProduct", "Desc", -10.0, "Broken");
        });

        assertTrue(exception.getMessage().contains("Price cannot be negative"));
    }

    @Test
    public void testFactory() throws CatalogException {
        // Test Factory
        Product product = CatalogFactory.createProduct("FactoryProduct", "Desc", 50.0, "New");
        assertNotNull(product, "Il prodotto creato dalla factory non deve essere nullo");
        assertEquals(50.0, product.getPrice());
    }
}
