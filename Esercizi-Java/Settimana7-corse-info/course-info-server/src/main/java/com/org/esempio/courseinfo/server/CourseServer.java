package com.org.esempio.courseinfo.server;

import com.org.courseinforepository.CourseRepository;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;
import java.util.logging.LogManager;

public class CourseServer {

    static {
        // Questo è un blocco statico: viene eseguito una sola volta quando la classe viene caricata in memoria,
        // prima che qualsiasi istanza venga creata o che vengano chiamati metodi statici della classe.

        LogManager.getLogManager().reset();
        // Resetta la configurazione del LogManager di Java Util Logging (JUL),
        // rimuovendo eventuali handler o impostazioni precedenti.
        // Serve a evitare conflitti tra diverse configurazioni di logging nella stessa applicazione.

        SLF4JBridgeHandler.install();
        // Installa un bridge che intercetta i messaggi di log provenienti da JUL e li reindirizza a SLF4J.
        // In pratica, tutto ciò che prima andava su java.util.logging finisce ora nel framework SLF4J
        // (che può essere collegato a Logback, log4j o slf4j-simple).
        // Questo permette di avere un unico sistema di logging coerente, anche se alcune librerie usano JUL.
    }

    private static final Logger LOG = LoggerFactory.getLogger(CourseServer.class);

    private static final String BASE_URI = "http://localhost:8080/";
    // Indichiamo l'indirizzo e la porta su cui il server HTTP ascolterà le richieste.
    // In questo caso localhost:8080 significa che sarà raggiungibile solo dal computer locale.

    public static void main(String... args) {
        String databaseFilename = loadDatabaseFileName();
        LOG.info("Starting HTTP Server with database {}", databaseFilename);
        CourseRepository courseRepository =
                CourseRepository.openCourseRepository(databaseFilename);
        // Qui apriamo il repository dei corsi.
        // Il repository si occupa di leggere e salvare i dati dei corsi dal database
        // (qui un file courses.db).
        // Separare la gestione dei dati dal server permette di avere codice modulare
        // e più semplice da mantenere.

        ResourceConfig config = new ResourceConfig().register(new CourseResource(courseRepository));
        // Configuriamo Jersey creando un oggetto ResourceConfig.
        // Registriamo il nostro CourseResource, passando il repository come dipendenza.
        // In questo modo Jersey sa quali classi devono gestire le richieste REST.

        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
        // Creiamo e avviamo il server HTTP usando Grizzly.
        // Passiamo l'URI di base e la configurazione dei resource.
        // Da questo momento in poi il server è attivo e risponde alle richieste su http://localhost:8080/
    }

    private static String loadDatabaseFileName() {
        // Questo metodo statico legge il nome del file del database dai file di configurazione
        // e lo restituisce come stringa. Essendo statico, può essere chiamato senza creare un'istanza della classe.

        try (InputStream propertiesStream =
                     CourseServer.class.getResourceAsStream("/server.properties")) {
            // Apriamo uno stream per leggere il file "server.properties" che si trova nella cartella
            // resources del progetto.
            // Il try-with-resources garantisce che lo stream venga chiuso automaticamente alla fine,
            // anche se si verifica un errore.

            Properties properties = new Properties();
            // Creiamo un oggetto Properties, una struttura chiave-valore già pronta per gestire file .properties.

            properties.load(propertiesStream);
            // Carichiamo le proprietà dal file nello stream.
            // Ora possiamo leggere le configurazioni tramite chiave, ad esempio "course-info.database".

            return properties.getProperty("course-info.database");
            // Restituiamo il valore associato alla chiave "course-info.database",
            // cioè il percorso o il nome del file del database da usare nell'applicazione.

        } catch (IOException e) {
            // Se si verifica un errore di I/O durante la lettura del file (file mancante, problemi di accesso, ecc.),
            // catturiamo l'eccezione.

            throw new IllegalStateException("Could not load database filename");
            // Convertiamo l'eccezione in una IllegalStateException più generica,
            // perché senza il file di configurazione l'applicazione non può funzionare.
        }
    }
}
