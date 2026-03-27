package org.esempio.Service;

import org.esempio.Model.Speaker;
import java.util.List;

// Interfaccia Service (logica di business)
public interface SpeakerService {

    // Metodo per ottenere tutti gli speaker
    List<Speaker> findAll();
}