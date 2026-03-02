package org.esempio.courseinfo.cli.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CourseRetrievalService {
    // URL dell'endpoint remoto.
    // In questo caso punta a un file JSON su GitHub contenente dati di esempio.
    // La versione commentata sotto invece punta all'API reale di Pluralsight
    // dove %s verrebbe sostituito con l'authorId.
    private static final String PS_URI = "https://raw.githubusercontent.com/sandermak-ps/course-info-java-17/master/sander-mak.json";
    //private static final String PS_URI = "https://app.pluralsight.com/profile/data/author/%s/all-content";

    // HttpClient è il client effettuare richieste HTTP.
    // Viene creato una sola volta come costante statica (riutilizzabile).
    private static final HttpClient CLIENT = HttpClient

            // newBuilder() permette di configurare il client prima di crearlo
            .newBuilder()

            // followRedirects(...) imposta la gestione dei redirect HTTP.
            // Redirect.ALWAYS significa che il client seguirà automaticamente
            // tutti i redirect (301, 302, 307, 308).
            // Se il server risponde con un nuovo URL, il client
            // farà automaticamente una nuova richiesta verso quell'URL.
            .followRedirects(HttpClient.Redirect.ALWAYS)

            // build() crea l'oggetto HttpClient definitivo
            // con la configurazione specificata sopra.
            .build();

    // Metodo pubblico che recupera i corsi per uno specifico autore.
    // authorId serve per identificare l'autore.
    public String getCoursesFor(String authorId) {
        // Costruzione della richiesta HTTP:
        // - URI.create(...) crea l'oggetto URI partendo dalla stringa PS_URI
        // - formatted(authorId) sostituisce eventuali %s nell'URL con authorId
        // - GET() specifica che la richiesta è di tipo GET
        // - build() costruisce l'oggetto HttpRequest
        HttpRequest request = HttpRequest.newBuilder(URI.create(PS_URI.formatted(authorId)))
                .GET().build();
        try {
            // Invio della richiesta HTTP.
            // CLIENT.send(...) invia la richiesta e attende la risposta.
            // BodyHandlers.ofString() indica che il corpo della risposta
            // deve essere convertito in una String.
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            // Restituisce il corpo della risposta (contenuto JSON come stringa)
            // Usiamo uno switch expression (Java 14+) per gestire i casi.
            return switch (response.statusCode()) {

                // 200 = OK la richiesta è andata a buon fine.
                // Restituiamo il body della risposta (JSON come String).
                case 200 -> response.body();

                // 404 = Not Found la risorsa non esiste.
                // In questo caso restituiamo una stringa personalizzata.
                case 404 -> "Not Found";

                // Qualsiasi altro status code (es. 500, 403, ecc.)
                // viene considerato errore.
                // Lanciamo una RuntimeException con il codice ricevuto.
                default -> throw new RuntimeException(
                        "Pluralsight API call failed with status code "
                                + response.statusCode());
            };
        }catch (IOException | InterruptedException e) {
            // Se si verifica un errore di rete (IOException)
            // o il thread viene interrotto (InterruptedException),
            // viene lanciata una RuntimeException.
            // In questo modo il chiamante non è obbligato a gestire
            // l'eccezione checked.
            throw new RuntimeException("Could not call pluralsight API ", e);
        }
    }
}
