package carvedrockfitness.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class LoggingUtil {

    // Metodo per inizializzare il LogManager di Java utilizzando
    // un file di configurazione esterno (logging.properties).
    // Questo permette di impostare livelli di log, handler e formatter
    // senza modificare il codice, rendendo la configurazione centralizzata e flessibile.
    public static void initLogManager() {
        try {
            // Legge la configurazione dei logger dal file specificato.
            // FileInputStream apre il file "src/main/resources/logging.properties".
            // LogManager gestisce tutti i logger dell'applicazione.
            LogManager.getLogManager().readConfiguration(
                    new FileInputStream("Settimana6 esercizio sui log/src/main/resources/logging.properties")
            );
        } catch (IOException ex) {
            // Se si verifica un errore durante la lettura del file,
            // viene stampato lo stack trace. In alternativa si potrebbe
            // loggare l'errore su un logger di fallback.
            ex.printStackTrace();
        }
    }
}
