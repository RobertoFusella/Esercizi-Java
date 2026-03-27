package org.esempio.Repository;

import org.esempio.Model.Speaker;
import java.util.List;

// Interfaccia Repository
// Definisce il contratto per accedere ai dati
public interface SpeakerRepository {

    // Metodo per ottenere tutti gli speaker
    List<Speaker> findAll();
}