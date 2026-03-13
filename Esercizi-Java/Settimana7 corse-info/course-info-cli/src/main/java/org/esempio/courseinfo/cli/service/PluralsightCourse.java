package org.esempio.courseinfo.cli.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Duration;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PluralsightCourse(String id, String title, String duration, String contentUrl, boolean isRetired) {

    public long durationInMinutes() {
        // Converte la stringa 'duration' (es. "01:30:00") in minuti totali.
        // LocalTime.parse(duration) trasforma la stringa in un oggetto LocalTime.
        // LocalTime.MIN rappresenta mezzanotte (00:00).
        // Duration.between(...) calcola la differenza tra mezzanotte e l'orario del corso.
        // toMinutes() restituisce il totale dei minuti.
        return Duration.between(LocalTime.MIN,
                LocalTime.parse(duration)).toMinutes();
    }

}
