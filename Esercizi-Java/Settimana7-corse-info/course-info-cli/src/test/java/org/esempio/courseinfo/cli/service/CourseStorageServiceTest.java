package org.esempio.courseinfo.cli.service;

import com.org.courseinforepository.CourseRepository;
import com.org.esempio.courseinfo.domain.Course;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseStorageServiceTest {

    @Test
    void storePluralsightCourses() {

        // Qui creiamo una implementazione "finta" del repository.
        // Non stiamo usando un database vero perché il punto del test
        // NON è verificare la persistenza.
        // Vogliamo isolare il comportamento del CourseStorageService.
        // Per farlo gli passiamo una dipendenza sostituibile.
        // Questo funziona perché il service dipende dall'interfaccia
        // CourseRepository e non da una classe concreta.
        CourseRepository repository = new InMemoryCourseRepository();


        // Qui istanziamo la classe che contiene la logica di business.
        // Il repository viene passato nel costruttore:
        // questa è dependency injection.
        // Il service quindi non crea da solo il repository,
        // ma riceve qualcosa che "sa salvare corsi".
        CourseStorageService courseStorageService =
                new CourseStorageService(repository);


        // Questo oggetto rappresenta un corso così come arriva
        // da una fonte esterna (Pluralsight).
        // Quindi NON è nel formato che vogliamo nel nostro dominio.
        PluralsightCourse ps1 = new PluralsightCourse(
                "1",
                "Title 1",
                "01:40:00.123",
                "/url-1",
                false
        );


        // Qui chiamiamo il metodo da testare.
        // 1. Il service prende il PluralsightCourse.
        // 2. Converte la durata da stringa a minuti.
        //      "01:40:00.123" → 100 minuti
        // 3. Costruisce l'URL completo aggiungendo il dominio base.
        //      "/url-1" = "https://app.pluralsight.com/url-1"
        // 4. Crea un oggetto Course (modello interno).
        // 5. Lo salva nel repository chiamando saveCourse().
        // Quindi il service sta facendo da "traduttore"
        // tra un modello esterno e il nostro modello interno.
        courseStorageService.storePluralsightCourses(List.of(ps1));


        // Questo è l'oggetto che CI ASPETTIAMO venga salvato.
        // Non è un PluralsightCourse, ma un Course.
        // È già nel formato normalizzato del dominio:
        // - durata in minuti (int)
        // - URL completo
        // Se il service ha fatto bene il suo lavoro,
        // nel repository ci sarà esattamente questo oggetto.
        Course expected = new Course(
                "1",
                "Title 1",
                100,
                "https://app.pluralsight.com/url-1",
                Optional.empty()
        );


        // Qui verifichiamo il risultato.
        // repository.getAllCourses() restituisce la lista interna
        // dove il service ha salvato il corso.
        // Confrontiamo la lista con quella attesa.
        // Questo confronto funziona SOLO se la classe Course
        assertEquals(List.of(expected), repository.getAllCourses());
    }



    // Questa è una implementazione semplificata del repository.
    // È definita dentro il test perché serve SOLO al test.
    // Non fa I/O, non usa database.
    // È solo un contenitore in memoria.
    static class InMemoryCourseRepository implements CourseRepository {

        // Lista che simula la tabella del database.
        // Tutto viene salvato qui dentro.
        private final List<Course> courses = new ArrayList<>();


        @Override
        public void saveCourse(Course course) {

            // Questo metodo simula la persistenza.
            // Aggiungiamo semplicemente l'oggetto alla lista.
            // Questo ci permette di osservare cosa il service
            // ha deciso di salvare.
            courses.add(course);
        }

        @Override
        public void addNote(String id, String notes) {
            throw new UnsupportedOperationException("Not supported yet.");
        }


        @Override
        public List<Course> getAllCourses() {

            // Restituiamo la lista dei corsi salvati.
            // Serve al test per poter verificare il risultato.
            return courses;
        }
    }
}