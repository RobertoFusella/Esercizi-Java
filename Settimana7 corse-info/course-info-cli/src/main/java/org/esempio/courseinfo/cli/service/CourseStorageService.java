package org.esempio.courseinfo.cli.service;

import com.org.courseinforepository.CourseRepository;
import com.org.esempio.courseinfo.domain.Course;

import java.util.List;

public class CourseStorageService {

    // URL base della piattaforma Pluralsight.
    // Serve per costruire l'URL completo di ogni corso concatenandolo con contentUrl.
    // Ad esempio, contentUrl = "/library/courses/java" → URL finale = "https://app.pluralsight.com/library/courses/java"
    private static final String PS_BASE_URL = "https://app.pluralsight.com";

    // Riferimento al repository dei corsi.
    // Il service NON conosce i dettagli di persistenza (JDBC, file, memoria).
    // Conosce solo l'interfaccia CourseRepository.
    // Questo rende la classe disaccoppiata, testabile e flessibile.
    private final CourseRepository courseRepository;

    // Costruttore con dependency injection.
    // L'oggetto repository viene passato dall'esterno.
    // Questo consente di:
    // - cambiare implementazione del repository senza modificare il service
    // - testare il service usando un mock o fake repository
    public CourseStorageService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Metodo principale che riceve una lista di corsi da Pluralsight e li salva nel repository
    public void storePluralsightCourses(List<PluralsightCourse> psCourses) {

        // Ciclo for-each su tutti i corsi ricevuti.
        // psCourse rappresenta un singolo PluralsightCourse alla volta.
        for (PluralsightCourse psCourse : psCourses) {

            // Trasformazione modello esterno → modello interno
            // PluralsightCourse rappresenta dati grezzi presi da Pluralsight.
            // Course è il modello interno che sarà salvato nel database.
            // Qui convertiamo:
            // - id = id del corso
            // - title = nome del corso
            // - durata in minuti = tramite metodo durationInMinutes()
            // - URL completo = concatenando PS_BASE_URL con contentUrl
            Course course = new Course(
                    psCourse.id(),
                    psCourse.title(),
                    psCourse.durationInMinutes(),
                    PS_BASE_URL + psCourse.contentUrl()
            );

            // Salvataggio del corso tramite repository
            // Il service delega completamente la persistenza al repository.
            // Non sa se il repository usa JDBC, H2, file o altro.
            // La responsabilità del service è solo trasformare e coordinare i dati.
            courseRepository.saveCourse(course);

            // Internamente, se courseRepository è CourseJdbcRepository:
            // - viene aperta una Connection al database
            // - viene creato un PreparedStatement
            // - vengono impostati i parametri (id, nome, durata, URL)
            // - la query MERGE INTO viene eseguita
            // - la Connection viene chiusa automaticamente
        }
    }
}