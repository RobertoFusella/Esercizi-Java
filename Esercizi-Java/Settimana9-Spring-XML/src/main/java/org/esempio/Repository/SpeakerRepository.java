package org.esempio.Repository;

import org.esempio.Model.Speaker;

import java.util.List;

public interface SpeakerRepository {
    List<Speaker> findAll();
}
