package com.org.esempio.courseinfo.server;


import com.org.courseinforepository.CourseRepository;
import com.org.courseinforepository.RepositoryException;
import com.org.esempio.courseinfo.domain.Course;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Path("/courses")
// Questo dice a Jersey che questa classe gestisce le richieste HTTP all'endpoint "/courses".
// Tutte le chiamate a /courses saranno instradate qui, e i metodi annotati
// con @GET, @POST ecc. risponderanno.
public class CourseResource {

    private static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);

    private final CourseRepository courseRepository;
    // Qui salviamo il repository dei corsi. La classe non sa come i dati vengono salvati o caricati,
    // si affida al repository per avere l'elenco dei corsi. Questo separa la logica di accesso ai dati
    // dalla logica di esposizione via REST.

    public CourseResource(CourseRepository courseRepository) {
        // Il repository viene passato al costruttore perché questa classe ha bisogno di accedere ai corsi.
        // In un’applicazione reale, il repository contiene i dati e gestisce la persistenza.
        // Passandolo nel costruttore, il resource non si preoccupa di come ottenere i dati,
        // si concentra solo su come esporli tramite REST.
        // Questo permette di cambiare facilmente il repository (ad esempio uno reale su database o uno in memoria)
        // senza modificare il resource.
        this.courseRepository = courseRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Stream<Course> getCourses() {
        // Questo metodo risponde alle richieste HTTP GET sull'endpoint /courses.
        // La risposta sarà in formato JSON grazie a @Produces(MediaType.APPLICATION_JSON>,
        // quindi i client riceveranno i dati dei corsi come array JSON.

        // Usiamo try-catch perché il repository potrebbe generare un'eccezione
        // durante l'accesso ai dati (ad esempio file mancante o errore di lettura).
        try {
            // Prendiamo tutti i corsi dal repository.
            // Convertiamo la lista in uno Stream per poterla elaborare in pipeline.
            // Ordinamento basato sul campo id tramite Comparator.comparing(Course::id).
            // Restituiamo lo Stream direttamente invece di convertirlo in lista.
            // Questo permette di processare i dati in modo “lazy” senza creare subito una lista in memoria.
            return courseRepository
                    .getAllCourses()
                    .stream()
                    .sorted(Comparator.comparing(Course::id));
        } catch (RepositoryException e) {
            // Se c’è un problema nell’accesso ai dati, logghiamo l’errore con un messaggio chiaro.
            LOG.error("Could not retrieve courses from the database", e);
            // Lanciamo una NotFoundException per far sapere al client che non è stato possibile recuperare i corsi.
            // Jersey convertirà questa eccezione in un HTTP 404.
            throw new NotFoundException();
        }
    }

    @POST
    @Path("/{id}/notes")
    @Consumes(MediaType.TEXT_PLAIN)
    public void addNotes(@PathParam("id") String id, String notes) {
        // Questo metodo risponde alle richieste HTTP POST sull'endpoint /courses/{id}/notes.
        // L'annotazione @Path("/{id}/notes") indica che l'id del corso viene preso dall'URL.
        // Ad esempio, una richiesta POST a /courses/123/notes farà passare "123" come parametro id.

        // @Consumes(MediaType.TEXT_PLAIN) specifica che il corpo della richiesta contiene testo semplice,
        // cioè la nota da salvare.

        // Il metodo chiama il repository per aggiungere la nota al corso con l'id specificato.
        // La logica di persistenza dei dati (scrittura su file o database) è delegata al repository,
        // quindi il resource si concentra solo sull’esposizione REST.
        courseRepository.addNote(id, notes);
    }
}
