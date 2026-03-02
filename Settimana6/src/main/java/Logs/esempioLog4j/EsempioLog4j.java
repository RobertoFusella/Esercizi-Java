package Logs.esempioLog4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class EsempioLog4j {
    public static void main(String[] args) {
        // Creiamo un Logger per questa classe
        // Serve per registrare messaggi di log con diversi livelli (INFO, ERROR, ecc.)
        Logger logger = LogManager.getLogger(EsempioLog4j.class);
        // Scrive un messaggio di livello INFO
        // INFO: messaggi informativi sul normale flusso dell'applicazione
        logger.info("Provando Logger Log4j EsempioLog4j - INFO");
        // Scrive un messaggio di livello ERROR con eccezione allegata
        // ERROR: messaggi che indicano un errore grave
        // L'eccezione (IOException) viene mostrata nello stack trace del log
        logger.error("Provando Logger Log4j EsempioLog4j - ERROR", new IOException());
    }
}
