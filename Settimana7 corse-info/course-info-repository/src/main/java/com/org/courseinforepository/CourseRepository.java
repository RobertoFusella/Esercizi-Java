package com.org.courseinforepository;

import com.org.esempio.courseinfo.domain.Course;

import java.util.List;

// Interfaccia che definisce le operazioni di persistenza dei corsi
// Ogni implementazione concreta può usare JDBC, file, memoria ecc.
public interface CourseRepository {

    // Salva un singolo corso nel repository.
    // Il service non deve conoscere come viene salvato (DB, file, ecc.)
    void saveCourse(Course course);

    void addNote(String id, String notes);

    // Restituisce tutti i corsi presenti nel repository.
    // Ritorna una lista di oggetti Course.
    List<Course> getAllCourses();

    // Metodo statico factory che restituisce un repository concreto.
    // In questo caso crea un CourseJdbcRepository.
    static CourseRepository openCourseRepository(String databaseFile){
        // CourseJdbcRepository gestirà internamente JDBC/H2.
        return new CourseJdbcRepository(databaseFile);
    }
}