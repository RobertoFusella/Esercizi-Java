package org.esempio.courseinfo.cli;

import com.org.courseinforepository.CourseRepository;
import org.esempio.courseinfo.cli.service.CourseRetrievalService;
import org.esempio.courseinfo.cli.service.CourseStorageService;
import org.esempio.courseinfo.cli.service.PluralsightCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

// Classe CLI (Command Line Interface) che esegue l'applicazione
public class CourseRetriever {

    // Logger SLF4J per stampare messaggi su console o file
    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String... args) {
        LOG.info("CourseRetriever is starting...");

        // Controllo argomenti: serve almeno il nome/autore dell'autore
        if(args.length == 0){
            LOG.warn("Please provide an author name as first argument");
            return;
        }

        try {
            // Recupera e salva i corsi dell'autore passato come argomento
            retrieverCourses(args[0]);
        } catch(Exception e){
            // Log degli errori inattesi
            LOG.error("Unexpected error occurred", e);
        }
    }

    // Metodo privato che coordina il recupero e la memorizzazione dei corsi
    private static void retrieverCourses(String authorId) {
        LOG.info("Retrieving courses for author '{}'", authorId);

        // 1. Creo il service per recuperare i corsi da Pluralsight
        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();

        // 2. Creo il repository dei corsi salvati
        // openCourseRepository restituisce un CourseJdbcRepository configurato su "./courses.db"
        CourseRepository courseRepository = CourseRepository.openCourseRepository("./courses.db");

        // 3. Creo il service per memorizzare i corsi nel repository
        CourseStorageService courseStorageService = new CourseStorageService(courseRepository);

        // 4. Recupero i corsi da Pluralsight per l'autore
        //    Filtrando quelli che non sono ritirati (isRetired = false)
        List<PluralsightCourse> coursesToStore = courseRetrievalService.getCoursesFor(authorId)
                .stream()
                .filter(course -> !course.isRetired())
                .toList();

        LOG.info("Retrieved the following {} courses: {}", coursesToStore.size(), coursesToStore);

        // 5. Memorizzo i corsi nel repository
        courseStorageService.storePluralsightCourses(coursesToStore);

        LOG.info("Courses successfully stored");
    }
}