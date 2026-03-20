package org.esempio;

import org.esempio.model.Book;
import org.esempio.repository.BookDao;
import org.esempio.repository.Dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {

        // Crea un'istanza del DAO per i libri
        // BookDao implementa Dao<Book> e gestisce tutte le operazioni sul DB
        Dao<Book> bookDao = new BookDao();
        // Recupera tutti i libri dal database
        List<Book> books = bookDao.findAll();

        // Ciclo attraverso la lista di libri e stampo i valori di ogni libro
        for (Book book : books) {
            System.out.println("id: " + book.getId());       // stampa l'ID del libro
            System.out.println("title: " + book.getTitle()); // stampa il titolo del libro
            System.out.println("rating: " + book.getRating());
        }

        // Cerca un libro specifico tramite ID (in questo caso l'ID 1)
        Optional<Book> optBook = bookDao.findById(1);

        // Controlla se il libro esiste
        if (optBook.isPresent()) {
            // Se esiste, recupera l'oggetto Book dall'Optional
            Book book = optBook.get();
            System.out.println("id: " + book.getId());       // stampa l'ID del libro trovato
            System.out.println("title: " + book.getTitle()); // stampa il titolo del libro trovato

            book.setTitle("Effective Java Second Edition");
            bookDao.update(book);
        }

        // Crea un nuovo oggetto Book
        Book newBook = new Book();
        newBook.setTitle("The River Why"); // imposta il titolo del nuovo libro

        // Inserisce il nuovo libro nel database tramite il DAO
        // Il metodo create restituisce il libro completo anche con l'ID generato dal DB
        newBook = bookDao.create(newBook);

        // Stampa l'ID generato e il titolo del nuovo libro appena inserito
        System.out.println("id: " + newBook.getId());
        System.out.println("title: " + newBook.getTitle());

        int numDel = bookDao.delete(newBook);

        System.out.println("Number of records deleted: " + numDel);

        //books = bookDao.findAll();
        // Recupera tutti i libri dal database e li mette nella lista "books"

        //List<Book> updatedEntries = books.stream()
                // crea uno stream (flusso) di oggetti Book

                //.peek(b -> b.setRating(5))
                // per ogni libro, imposta il rating a 5
                // peek NON è pensato per modificare dati

                //.collect(Collectors.toList());
                // raccoglie i risultati in una nuova lista (updatedEntries)

        //bookDao.update(updatedEntries);
        // chiama il metodo batch update per aggiornare tutti i libri nel database
    }
}
