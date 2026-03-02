package Logs.esempioSlf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EsempioSlf4j {
    // Creiamo un Logger per questa classe
    // LoggerFactory.getLogger() restituisce un Logger legato alla classe EsempioSlf4j
    // SLF4J è una "facade": non scrive i log direttamente, ma delega a un backend scelto tramite dipendenza Maven
    // A seconda della dipendenza che metti nel pom.xml (Log4j, Logback, JDK Logging...), il Logger scriverà in quel backend
    // Questo permette di cambiare libreria di log senza modificare il codice
    final static Logger LOGGER = LoggerFactory.getLogger(EsempioSlf4j.class);

    public static void main(String args[]) {
        LOGGER.info("messaggio log con livello info");
        LOGGER.error("messaggio log con livello error");
    }
}
