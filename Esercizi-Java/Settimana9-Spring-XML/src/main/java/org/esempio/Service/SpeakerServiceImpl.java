package org.esempio.Service;

import org.esempio.Model.Speaker;
import org.esempio.Repository.SpeakerRepository;

import java.util.List;

public class SpeakerServiceImpl implements SpeakerService {

    private SpeakerRepository repository;

    public SpeakerServiceImpl(SpeakerRepository repository) {
        this.repository = repository;
    }

    public void setSpeakerRepository(SpeakerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Speaker> findAll() {
        return repository.findAll();
    }

}
