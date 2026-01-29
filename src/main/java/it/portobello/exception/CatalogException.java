package it.portobello.exception;

// Estendiamo Exception (e non RuntimeException) per creare una "Checked Exception".
// Questo OBBLIGA chi usa il tuo codice a gestire l'errore, garantendo "Controlled Exception Propagation"[cite: 15].
public class CatalogException extends Exception {

    public CatalogException(String message) {
        super(message);
    }

    // Possiamo aggiungere costruttori extra se vogliamo passare anche la causa originale
    public CatalogException(String message, Throwable cause) {
        super(message, cause);
    }
}