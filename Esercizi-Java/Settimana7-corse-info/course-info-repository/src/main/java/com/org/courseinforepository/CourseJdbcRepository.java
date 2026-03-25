package com.org.courseinforepository;

import com.org.esempio.courseinfo.domain.Course;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CourseJdbcRepository implements CourseRepository {

    // Stringa di connessione JDBC per il database H2.
    // jdbc:h2:file: indica che il database è salvato su file.
    // %s è un placeholder che verrà sostituito con il nome del file.
    // AUTO_SERVER=TRUE permette connessioni multiple.
    // INIT=RUNSCRIPT esegue automaticamente uno script SQL all’avvio.
    private static final String H2_DATABASE_URL =
            "jdbc:h2:file:%s;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM 'Settimana7 corse-info/db_init.sql'";

    // Query SQL parametrizzata.
    // MERGE INTO in H2 significa:
    // se il record esiste viene aggiornato,
    // se non esiste viene inserito.
    // I punti interrogativi sono placeholder per i parametri.
    // Questo rende la query sicura e precompilata(evita SQL injection).
    private static final String INSERT_COURSE = """
            MERGE INTO Courses (id,name,length,url)
                VALUES (?,?,?,?)
            """;
    // Query SQL parametrizzata.
    // Questa stringa contiene un comando SQL che aggiorna la colonna "notes" della tabella "Courses"
    // per un corso specifico identificato dal suo "id".
    // I punti di domanda (?) sono parametri placeholder, che verranno sostituiti in modo sicuro
    // quando eseguiremo la query con un PreparedStatement.
    // Questo evita problemi di SQL injection e permette di passare dinamicamente valori di note e id.
    private static final String ADD_NOTES = """
            UPDATE Courses SET notes = ?
            WHERE id = ?
            """;

    // DataSource è una fabbrica di connessioni.
    // Non è una connessione attiva.
    // È un oggetto che sa creare Connection configurate correttamente.
    private final DataSource dataSource;

    // Costruttore che configura il DataSource.
    public CourseJdbcRepository(String databaseFile) {

        // JdbcDataSource è l’implementazione concreta di DataSource
        // fornita dal database H2.
        JdbcDataSource jdbcdataSource = new JdbcDataSource();

        // formatted(databaseFile) sostituisce %s con il nome del file.
        // In questo modo ogni istanza può usare un database diverso.
        jdbcdataSource.setURL(H2_DATABASE_URL.formatted(databaseFile));

        // Salviamo la factory di connessioni nel campo della classe.
        this.dataSource = jdbcdataSource;
    }

    @Override
    public void saveCourse(Course course) {

        // getConnection apre una connessione attiva al database.
        // Connection rappresenta una sessione di comunicazione
        // tra l’applicazione Java e il database.
        // try-with-resources garantisce la chiusura automatica.
        try (Connection connection = dataSource.getConnection()) {

            // PreparedStatement è una versione sicura e precompilata di Statement.
            // Quando viene creato:
            // il database analizza la query,
            // la ottimizza,
            // la prepara per l’esecuzione.
            PreparedStatement statement =
                    connection.prepareStatement(INSERT_COURSE);

            // Ogni set assegna un valore al placeholder(?) corrispondente.
            // L’indice parte da 1.
            // JDBC converte automaticamente i tipi Java nei tipi SQL.
            statement.setString(1, course.id());
            statement.setString(2, course.name());
            statement.setLong(3, course.length());
            statement.setString(4, course.url());

            // execute invia la query al database.
            // Il database esegue l’operazione di insert o update.
            statement.execute();

        } catch (SQLException e) {

            // SQLException è un’eccezione tecnica JDBC.
            // La trasformiamo in RepositoryException
            // per non esporre dettagli di basso livello
            // agli strati superiori dell’applicazione.
            throw new RepositoryException("Failed to save " + course, e);
        }
    }

    @Override
    public void addNote(String id, String notes) {
        // Questo metodo aggiorna la colonna "notes" di un corso nel database identificato dal suo id.
        // Viene passato l'id del corso e la nota da salvare come parametri.

        try (Connection connection = dataSource.getConnection()) {
            // Otteniamo una connessione al database dal dataSource.
            // Il try-with-resources garantisce che la connessione venga chiusa automaticamente
            // alla fine del blocco, anche se si verifica un errore.

            PreparedStatement statement =
                    connection.prepareStatement(ADD_NOTES);
            // Creiamo un PreparedStatement usando la query SQL parametrizzata definita in ADD_NOTES.
            // Questo permette di inserire valori in modo sicuro senza rischiare SQL injection.

            statement.setString(1, notes);
            statement.setString(2, id);
            // Impostiamo i parametri della query:
            // 1° ? → notes, 2° ? → id. Così la query aggiornerà solo il corso corretto con la nota fornita.

            statement.execute();
            // Eseguiamo la query di aggiornamento sul database.
            // Non serve gestire il risultato qui perché UPDATE non restituisce dati da leggere.

        } catch (SQLException e) {
            // Se si verifica un errore durante la connessione o l'esecuzione della query,
            // viene catturata un'eccezione SQLException.

            throw new RepositoryException("Failed to add notes to " + id, e);
            // Convertiamo l'eccezione SQL in una RepositoryException più generica,
            // aggiungendo il messaggio con l'id del corso per dare contesto.
            // Questo rende più semplice la gestione degli errori a livelli superiori.
        }
    }

    @Override
    public List<Course> getAllCourses() {

        // Nuova connessione per l’operazione di lettura.
        try (Connection connection = dataSource.getConnection()) {

            // Statement è usato per query statiche senza parametri.
            Statement statement = connection.createStatement();

            // executeQuery esegue una SELECT.
            // Restituisce un ResultSet.
            // ResultSet rappresenta una tabella risultato prodotta dal database.
            ResultSet resultSet =
                    statement.executeQuery("SELECT * FROM COURSES");

            // Lista che conterrà i corsi convertiti in oggetti Java.
            List<Course> courses = new ArrayList<>();

            // ResultSet utilizza un cursore.
            // All’inizio il cursore è prima della prima riga.
            // next sposta il cursore alla riga successiva.
            // Restituisce false quando non ci sono più righe.
            while (resultSet.next()) {

                // getString(1) legge la prima colonna della riga corrente.
                // getLong(3) legge la terza colonna come long.
                // Qui stiamo trasformando una riga SQL in un oggetto Java.
                Course course = new Course(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getLong(3),
                        resultSet.getString(4),
                        Optional.ofNullable(resultSet.getString(5)));
                // Legge il valore della quinta colonna della riga corrente dal ResultSet.
                // Se il campo nel database contiene un valore,
                // questo viene inserito in un Optional<String>.
                // Se il campo è NULL nel database, restituisce Optional.empty() invece di null.
                // Questo permette di gestire in modo sicuro valori
                // opzionali senza rischiare NullPointerException,
                // perché Optional fornisce metodi come
                // ifPresent(), map() o orElse() per lavorare con il valore in modo fluido.
                courses.add(course);
            }

            // Restituiamo una lista non modificabile.
            // Questo impedisce modifiche esterne ai dati.
            return Collections.unmodifiableList(courses);

        } catch (SQLException e) {

            // Anche qui convertiamo l’eccezione tecnica
            // in una eccezione di dominio del repository.
            throw new RepositoryException(
                    "Failed to retrieve courses", e);
        }
    }
}