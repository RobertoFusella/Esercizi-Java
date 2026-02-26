package carvedrockfitness.order;

import carvedrockfitness.user.User;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderController {
    private final OrderService orderService = new OrderService();
    private static final Logger LOGGER = Logger.getLogger(OrderController.class.getName());

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
//            fileHandler = new FileHandler(OrderController.class.getSimpleName() + ".log");
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

    //get all endpoint
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    //get by carvedrockfitness.user
    public List<Order> getAllOrdersByUser(User user) {
        return orderService.getAllOrdersByUser(user);
    }

    //post endpoint
    public boolean addOrder(Order order) {
        LOGGER.log(Level.FINE, "At endpoint order, with order details: " + order);
        return orderService.addOrder(order);
    }

    //delete
    public boolean deleteOrder(Order order) {
        return orderService.deleteOrder(order);
    }

}
