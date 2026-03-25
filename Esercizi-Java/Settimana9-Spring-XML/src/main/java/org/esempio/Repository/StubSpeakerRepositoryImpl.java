package org.esempio.Repository;

import org.esempio.Model.Speaker;

import java.util.ArrayList;
import java.util.List;
public class StubSpeakerRepositoryImpl implements SpeakerRepository {

    @Override
    public List<Speaker> findAll() {
        List<Speaker> speakers = new ArrayList<>();

        Speaker speaker = new Speaker();

        speaker.setFirstName("Bryan");
        speaker.setLastName("Hansen");

        speakers.add(speaker);

        return speakers;
    }

}
