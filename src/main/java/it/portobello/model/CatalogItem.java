package it.portobello.model;

/**
 * Interfaccia base per tutti gli elementi del catalogo.
 * Implementa il pattern Composite, permettendo di trattare uniformemente
 * oggetti singoli (Product) e contenitori (Category).
 */
public interface CatalogItem {
    double getPrice();
    String getName();
    void printDetails();
}