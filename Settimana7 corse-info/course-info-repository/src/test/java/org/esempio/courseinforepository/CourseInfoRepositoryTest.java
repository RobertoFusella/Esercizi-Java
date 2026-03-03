package org.esempio.courseinforepository;

// Import della classe Course che contiene il metodo filled()
import com.org.esempio.courseinfo.domain.Course;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CourseInfoRepositoryTest {

    // Questo test verifica che venga lanciata un'eccezione
    // quando la stringa è vuota ("")
    @Test
    void notFilled() {

        // assertThrows controlla che il codice nella lambda
        // lanci una IllegalArgumentException.
        // Se NON viene lanciata, il test fallisce.
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> Course.filled(""));

        // Qui controlliamo anche che il messaggio dell'eccezione
        // sia esattamente "No value present"
        assertEquals("No value present", exception.getMessage());
    }

    // Questo test verifica che venga lanciata un'eccezione
    // quando il valore è null
    @Test
    void FilledNull() {

        // Anche qui il test passa solo se viene lanciata
        // IllegalArgumentException
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> Course.filled(null));
    }

    // ATTENZIONE: questo test è volutamente sbagliato
    // perché "prova" è una stringa valida e NON dovrebbe
    // lanciare nessuna eccezione.
    @Test
    @Disabled
    void isFilled() {

        // Questo farà fallire il test,
        // perché non verrà lanciata alcuna eccezione.
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> Course.filled("prova"));
    }
}