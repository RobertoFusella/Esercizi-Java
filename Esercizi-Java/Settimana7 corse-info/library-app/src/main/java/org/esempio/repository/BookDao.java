package org.esempio.repository;

import org.esempio.model.Book;

import java.sql.*;
import java.util.*;

// La classe BookDao gestisce le operazioni CRUD per la tabella BOOK
// Estende AbstractDao, quindi eredita il metodo getConnection() che crea la connessione al DB
// Implementa Dao<Book>, quindi deve definire i metodi dichiarati in quell'interfaccia
public class BookDao extends AbstractDao implements Dao<Book> {

    @Override
    public Optional<Book> findById(long id) {
        // Inizializza l'oggetto Optional<Book> come vuoto
        // Optional serve per rappresentare il fatto che potrebbe non esserci un libro con quell'id
        Optional<Book> book = Optional.empty();

        // Query SQL con parametro (?) per selezionare un libro specifico tramite ID
        String sql = "SELECT ID, TITLE FROM BOOK WHERE ID = ?";

        // Try-with-resources: apre la connessione e il PreparedStatement e li chiude automaticamente
        try (
                // Ottiene la connessione al database (metodo ereditato da AbstractDao)
                Connection con = getConnection();

                // PreparedStatement permette di eseguire query con parametri in modo sicuro
                PreparedStatement prepStmt = con.prepareStatement(sql);
        ) {
            // Imposta il parametro della query (?) con il valore dell'id passato al metodo
            // Il primo parametro della query è indicato da 1
            prepStmt.setLong(1, id);

            // Esegue la query e ottiene i risultati in un ResultSet
            try (ResultSet rset = prepStmt.executeQuery()) {
                // Crea un oggetto Book che verrà popolato se la query ritorna un risultato
                Book resBook = new Book();

                // Se il ResultSet contiene almeno una riga (rset.next() restituisce true)
                if (rset.next()) {
                    // Legge i valori delle colonne ID e TITLE e li assegna all'oggetto Book
                    resBook.setId(rset.getLong("ID"));
                    resBook.setTitle(rset.getString("TITLE"));

                    // Avvolge l'oggetto Book dentro un Optional e lo assegna alla variabile book
                    book = Optional.of(resBook);
                }
                // Se rset.next() è false, Optional rimane vuoto, significa che non esiste un libro con quell'id
            }

        } catch (SQLException sqe) {
            // In caso di errore SQL, stampa lo stack trace
            sqe.printStackTrace();
        }

        // Restituisce l'Optional<Book>
        // Se il libro esiste, contiene il libro; altrimenti è vuoto
        return book;
    }

    // Implementazione del metodo findAll() dell'interfaccia Dao
    // Restituisce tutti i libri presenti nel database
    @Override
    public List<Book> findAll() {
        // Inizializza la lista dei libri come vuota (Collections.emptyList è immutabile)
        List<Book> books = Collections.emptyList();

        // Definisce la query SQL da eseguire
        // In questo caso seleziona tutte le colonne dalla tabella BOOK
        String sql = "SELECT * FROM BOOK";

        // Try-with-resources: apre e chiude automaticamente le risorse (Connection, Statement, ResultSet)
        try (
                // Ottiene la connessione al database tramite il metodo ereditato da AbstractDao
                Connection con = getConnection();

                // Crea un Statement che permette di eseguire query SQL statiche
                Statement stmt = con.createStatement();

                // Esegue la query SQL e ottiene i risultati in un ResultSet
                ResultSet rset = stmt.executeQuery(sql)
        ) {
            // inizializziamo la lista dei libri come ArrayList (modificabile)
            books = new ArrayList<>();

            // Ciclo attraverso tutte le righe del ResultSet
            // rset.next() restituisce true finché ci sono righe
            while (rset.next()) {
                // Crea un nuovo oggetto Book per ogni riga
                Book book = new Book();

                // Imposta l'id del libro prendendolo dalla colonna "id"
                book.setId(rset.getLong("id"));

                // Imposta il titolo del libro prendendolo dalla colonna "title"
                book.setTitle(rset.getString("title"));

                // Aggiunge l'oggetto Book appena creato alla lista dei libri
                books.add(book);
            }

        } catch (SQLException sqe) {
            // In caso di errore SQL, stampa lo stack trace
            sqe.printStackTrace();
        }

        // Restituisce la lista dei libri trovati
        // Se la query non ha trovato nulla o c'è stato un errore, restituisce la lista vuota
        return books;
    }

    @Override
    public Book create(Book book) {
        // Query SQL per inserire un nuovo libro nella tabella BOOK
        // La colonna TITLE sarà valorizzata con il parametro passato
        String sql = "INSERT INTO BOOK (TITLE) VALUES (?)";

        // Statement.RETURN_GENERATED_KEYS serve per recuperare l'ID generato automaticamente dal DB
        try (
                Connection con = getConnection(); // apre la connessione al database
                PreparedStatement prepStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            // Imposta il valore del parametro (?) con il titolo del libro
            prepStmt.setString(1, book.getTitle());

            // Esegue l'INSERT nel database
            // executeUpdate() ritorna il numero di righe modificate (in questo caso 1)
            prepStmt.executeUpdate();

            // Recupera le chiavi generate automaticamente dal database (ID AUTO_INCREMENT)
            try (ResultSet genKeys = prepStmt.getGeneratedKeys()) {
                // Se il database ha generato una chiave (ID)
                if (genKeys.next()) {
                    // Imposta l'ID generato nell'oggetto Book passato come parametro
                    book.setId(genKeys.getLong(1));
                }
            }

        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }

        // Restituisce l'oggetto Book, ora completo anche di ID generato dal database
        return book;
    }


}