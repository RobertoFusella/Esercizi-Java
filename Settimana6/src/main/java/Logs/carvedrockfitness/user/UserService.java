package Logs.carvedrockfitness.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

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
//            fileHandler = new FileHandler(UserService.class.getSimpleName() + ".log");
//        } catch (IOException ex) {
//            // Se c'è un errore durante la creazione del file di log,
//            // stampiamo lo stack trace (in alternativa si potrebbe loggare l'errore su un altro logger).
//            ex.printStackTrace();
//        }
//
//        // Imposta il livello minimo per l'handler a FINE.
//        // Solo i messaggi di livello FINE e superiori saranno scritti sul file.
//        fileHandler.setLevel(Level.FINE);
//
//        // Aggiunge l'handler al logger.
//        // Questo collega il logger all'output su file.
//        // Senza aggiungere l'handler, i log non verrebbero salvati sul file.
//        LOGGER.addHandler(fileHandler);
//    }

    //get all logic
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //get by carvedrockfitness.user.UserStatus logic
    public List<User> getAllUsersByUserStatus(UserStatus userStatus) {
        if (!userStatus.equals(UserStatus.DELETED)) {
            return userRepository.findByUserStatus(userStatus);
        } else {
            try {
                throw new Exception("Users with UserStatus cannot be get, because they formally don't exist.");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }

    //add carvedrockfitness.user logic
    public boolean addUser(User user) {
        if (user.getDateCreated().isAfter(LocalDateTime.now())) {

            // Log a livello WARNING perché la situazione è anomala:
            // si sta tentando di creare un utente con una data futura.
            // Non è ancora un errore tecnico del sistema, ma è un comportamento
            // sospetto o non valido dal punto di vista logico.
            // Il log include i dettagli dell'oggetto user per permettere
            // di analizzare il problema successivamente nei file di log.
            LOGGER.log(Level.WARNING, "Trying to create a user " +
                    "with a creation date that's in the future. User details: " + user);
            try {
                throw new Exception("Can't carvedrockfitness.user.datecreated in the future!");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        // Log a livello INFO perché rappresenta un evento normale
        // nel flusso dell'applicazione: l'aggiunta di un nuovo utente.
        // Serve per tracciare le operazioni effettuate dal sistema.
        LOGGER.log(Level.INFO, "Adding user, with user details: " + user);

        return userRepository.save(user);
    }

    //delete logic
    public boolean deleteUser(User user) {
        if (user.getUserStatus() == UserStatus.DELETED) {
            try {
                throw new Exception("Can't delete a deleted carvedrockfitness.user!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userRepository.remove(user);
    }
}
