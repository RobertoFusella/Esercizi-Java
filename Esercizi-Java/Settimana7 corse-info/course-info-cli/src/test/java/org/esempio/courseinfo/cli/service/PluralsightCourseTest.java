package org.esempio.courseinfo.cli.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PluralsightCourseTest {

    // Questa classe serve a testare il comportamento della classe PluralsightCourse,
    // in particolare il metodo durationInMinutes().
    // Tutti i test verificano che la conversione di una stringa di durata
    // (es. "01:08:54") in minuti totali funzioni correttamente.

    @ParameterizedTest
    @CsvSource(textBlock = """
            01:08:54.9613330, 68
            00:05:37, 5
            00:00:00, 0
            """)
    void durationInMinutes(String input, long expected) {
        // @ParameterizedTest indica che questo test verrà eseguito più volte,
        // una volta per ogni riga definita in @CsvSource.

        // @CsvSource(textBlock = "...") definisce i valori dei test:
        // - la prima colonna è la stringa di durata del corso (input)
        // - la seconda colonna è il numero di minuti atteso (expected)

        // Il metodo riceve due parametri:
        // - input: la durata da convertire
        // - expected: il valore atteso in minuti

        // In ogni esecuzione:
        // 1. Creiamo un oggetto PluralsightCourse usando 'input' come durata
        // 2. Chiamiamo il metodo durationInMinutes()
        // 3. Verifichiamo che il risultato sia uguale a 'expected'

        // Creazione del corso con dati fittizi, solo la durata è rilevante per il test
        PluralsightCourse course =
                new PluralsightCourse("id", "Test course", input, "url", false);

        // Verifica che il metodo calcoli correttamente i minuti
        // Se il risultato non corrisponde a expected, JUnit segnala un fallimento
        assertEquals(expected, course.durationInMinutes());
    }
}