package org.esempio.courseinfo.cli.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

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

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // Metodo pubblico che recupera i corsi per uno specifico autore.
    // authorId serve per identificare l'autore.
    public List<PluralsightCourse> getCoursesFor(String authorId) {
        // Il metodo restituisce una List<PluralsightCourse>.
        // List è un'interfaccia della collezione Java (java.util.List)
        // che rappresenta una sequenza ordinata di elementi.
        // In questo caso contiene oggetti di tipo PluralsightCourse,
        // quindi ogni elemento della lista rappresenta un corso.
        // La lista può contenere zero, uno o più corsi. Non accetta duplicati

        // Costruzione della richiesta HTTP:
        // - PS_URI.formatted(authorId) sostituisce eventuali placeholder (%s) nell'URL
        //   con l'authorId passato al metodo (se presenti).
        // - URI.create(...) crea un oggetto URI a partire dalla stringa finale.
        // - HttpRequest.newBuilder(...) crea un builder per configurare la richiesta.
        // - GET() specifica che il metodo HTTP utilizzato è GET.
        // - build() costruisce l'oggetto HttpRequest definitivo.
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(PS_URI.formatted(authorId)))
                .GET()
                .build();

        try {
            // Invio della richiesta HTTP:
            // - CLIENT.send(...) invia la richiesta in modo sincrono
            //   (il thread resta in attesa della risposta).
            // - BodyHandlers.ofString() indica che il corpo della risposta
            //   deve essere letto come String (quindi il JSON viene ricevuto come testo).
            HttpResponse<String> response =
                    CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            // Gestione del risultato in base allo status code HTTP.
            // Lo switch è un'espressione: restituisce direttamente
            // una List<PluralsightCourse>.
            return switch (response.statusCode()) {

                // 200 (OK):
                // La richiesta è andata a buon fine.
                // Convertiamo il JSON contenuto nel body in
                // una List<PluralsightCourse>.
                case 200 -> toPluralsightCourses(response);

                // 404 (Not Found):
                // L'autore o la risorsa non esiste.
                // Restituiamo una lista vuota.
                // List.of() crea una lista immutabile senza elementi.
                case 404 -> List.of();

                // Qualsiasi altro codice HTTP (es. 500, 403, ecc.)
                // viene trattato come errore.
                default -> throw new RuntimeException(
                        "Pluralsight API call failed with status code "
                                + response.statusCode());
            };

        } catch (IOException | InterruptedException e) {
            // IOException: errore di comunicazione di rete.
            // InterruptedException: il thread è stato interrotto durante l'attesa.
            // Rilanciamo come RuntimeException per evitare
            // di propagare eccezioni checked al chiamante.
            throw new RuntimeException("Could not call Pluralsight API", e);
        }
    }

    private static List<PluralsightCourse> toPluralsightCourses(HttpResponse<String> response)
            throws JsonProcessingException {

        // Creiamo il tipo di ritorno dinamicamente:
        // una List contenente oggetti PluralsightCourse.
        // È necessario perché a runtime Java perde
        // l'informazione sui generics (type erasure).
        // Java applica la "type erasure": a runtime l'informazione sui generics
        // (es. List<PluralsightCourse>) viene rimossa e resta solo List.
        // Per questo dobbiamo specificare esplicitamente a Jackson
        // che la lista contiene oggetti di tipo PluralsightCourse.

        JavaType returnType = OBJECT_MAPPER
                .getTypeFactory()
                .constructCollectionType(List.class, PluralsightCourse.class);

        // OBJECT_MAPPER.readValue(...) deserializza il JSON (String)
        // nel tipo specificato (List<PluralsightCourse>).
        // Il JSON ricevuto dall'API è un array di oggetti,
        // che viene convertito in una lista Java.
        return OBJECT_MAPPER.readValue(response.body(), returnType);
    }
}
