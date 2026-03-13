package Logs.carvedrockfitness.order;

import Logs.carvedrockfitness.user.User;
import Logs.carvedrockfitness.user.UserRepository;
import Logs.carvedrockfitness.user.UserStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderService {
    private OrderRepository orderRepository = new OrderRepository();
    private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());

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
//            fileHandler = new FileHandler(OrderService.class.getSimpleName() + ".log");
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
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    //get by carvedrockfitness.user logic
    public List<Order> getAllOrdersByUser(User user) {
        if (UserRepository.getDummyDataList().contains(user)) {
            return orderRepository.findByUser(user);
        } else {
            LOGGER.log(Level.WARNING, "User not found" + user);
            try {
                throw new Exception("The carvedrockfitness.user.User doesn't exist");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }

    //add carvedrockfitness.order logic
    public boolean addOrder(Order order) {
        if (order.getOrderDateTime().isAfter(LocalDateTime.now())) {
            try {
                LOGGER.log(Level.WARNING, "Trying to place an order in the future," +
                        "order details: " + order);
                throw new Exception("Can't place a carvedrockfitness.order.Order in the future!");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        if (order.getProducts().size() < 1) {
            try {
                LOGGER.log(Level.WARNING, "Trying to place an order with no products," +
                        "order details: " + order);
                throw new Exception("Order must consist of at least one carvedrockfitness.product.Product!");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        if (order.getUser().getUserStatus() == UserStatus.BLOCKED) {
            try {
                LOGGER.log(Level.WARNING, "Trying to place an order by a blocked user," +
                        "order details: " + order);
                throw new Exception("Order cannot be placed by blocked carvedrockfitness.user.User!");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else if (order.getUser().getUserStatus() == UserStatus.PENDING) {
            try {
                LOGGER.log(Level.WARNING, "Trying to place an order by a pending user," +
                        "order details: " + order);
                throw new Exception("Order cannot be placed by pending carvedrockfitness.user.User!");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        LOGGER.log(Level.INFO, "placing an order, order details: " + order);
        return orderRepository.save(order);
    }

    //delete logic
    public boolean deleteOrder(Order order) {
        if (order.getOrderStatus() == OrderStatus.COMPLETED) {
            try {
                throw new Exception("Can't cancel a completed carvedrockfitness.order.Order!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (order.getOrderStatus() == OrderStatus.CANCELLED) {
            try {
                throw new Exception("The carvedrockfitness.order.Order was already cancelled!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderRepository.remove(order);
    }

}
