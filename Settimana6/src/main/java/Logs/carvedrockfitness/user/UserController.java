package Logs.carvedrockfitness.user;


import java.util.List;
import java.util.logging.*;

public class UserController {
    private static final UserService userService = new UserService();

    // Creazione di un logger associato a questa classe.
    // Il logger servirà per scrivere messaggi di log (INFO, DEBUG, ERROR, ecc.)
    // relativi a UserController.
    private static final Logger LOGGER =
            // Ottiene (o crea, se non esiste) un Logger identificato
            // dal nome completo della classe UserController.
            // Questo nome verrà usato nei log per indicare
            // quale classe ha generato il messaggio.
            Logger.getLogger(UserController.class.getName());

    // ATTENZIONE: questi blocchi di inizializzazione statica per il logger
    // non sono più necessari se si utilizza LogManager.readConfiguration()
    // con un file logging.properties (come in LoggingUtil.initLogManager()).
    // Il file di configurazione globale gestisce livelli, handler, formatter
    // e filtri per tutti i logger dell'applicazione, rendendo ridondante
    // l'impostazione manuale dei logger in ogni classe.
    // I blocchi statici vanno mantenuti solo se si vuole una configurazione
    // personalizzata specifica per questa classe, diversa da quella globale.

//    static {
//        // Imposta il livello minimo di log del logger a FINE.
//        // Significa che il logger registrerà tutti i messaggi
//        // con livello FINE e superiori (FINE, INFO, WARNING, SEVERE).
//        LOGGER.setLevel(Level.FINE);
//
//        // Creiamo un handler per scrivere i log su file.
//        // FileHandler permette di salvare i messaggi di log in un file esterno.
//        FileHandler fileHandler = null;
//        try {
//            // Il file di log verrà creato con il nome della classe
//            // seguito da ".log", ad esempio "UserController.log".
//            fileHandler = new FileHandler(UserController.class.getSimpleName() + ".log");
//        } catch (IOException ex) {
//            // Se c'è un errore durante la creazione del file di log,
//            // stampiamo lo stack trace (in alternativa si potrebbe loggare l'errore su un altro logger).
//            ex.printStackTrace();
//        }
//
//        // Imposta il livello minimo per l'handler a FINE.
//        // Solo i messaggi di livello FINE e superiori saranno scritti sul file.
//        fileHandler.setLevel(Level.FINE);
//        // Imposta un formatter per l'handler del file.
//        // SimpleFormatter definisce il formato standard dei log scritti su file,
//        // includendo informazioni come timestamp, livello del log, nome del logger
//        // e il messaggio stesso.
//        fileHandler.setFormatter(new SimpleFormatter());
//        // Imposta un filtro personalizzato per l'handler del file.
//        // La lambda `s -> false` indica che **nessun log verrà accettato** dall'handler.
//        // In pratica, tutti i messaggi destinati a questo fileHandler saranno scartati.
//        // I filtri servono per controllare quali log vengono registrati,
//        // basandosi su condizioni personalizzate sul record di log.
//        fileHandler.setFilter(s -> false);
//        // Aggiunge l'handler al logger.
//        // Questo collega il logger all'output su file.
//        // Senza aggiungere l'handler, i log non verrebbero salvati sul file.
//        LOGGER.addHandler(fileHandler);
//    }

    //get all endpoint
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //get by carvedrockfitness.user.UserStatus
    public List<User> getAllUsersByUserStatus(UserStatus userStatus) {
        return userService.getAllUsersByUserStatus(userStatus);
    }

    //post endpoint
    public boolean addUser(User user) {

        // Scrive un messaggio di log a livello FINE.
        // LOGGER.log(...) utilizza il logger definito nella classe
        // per registrare un evento durante l'esecuzione del metodo.
        // Level.FINE è un livello di dettaglio usato principalmente
        // per il debugging: fornisce informazioni più approfondite
        // rispetto a INFO sul flusso interno dell'applicazione.
        // Di solito è attivo in ambiente di sviluppo e disattivato
        // in produzione per evitare eccessivo rumore nei log.
        // Il messaggio indica che siamo entrati nell'endpoint
        // per l'aggiunta di un utente e include i dettagli dell'oggetto user,
        // utile per analizzare passo per passo l'esecuzione del metodo.
        LOGGER.log(Level.FINE, "In endpoint for adding user, with these user details: " + user);

        return userService.addUser(user);
    }

    //delete
    public boolean deleteUser(User user) {
        return userService.deleteUser(user);
    }
}
