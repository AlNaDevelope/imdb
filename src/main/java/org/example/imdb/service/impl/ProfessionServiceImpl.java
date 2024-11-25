package org.example.imdb.service.impl;

import org.example.imdb.model.Profession;
import org.example.imdb.repository.ProfessionRepository;
import org.example.imdb.service.ProfessionService;
import org.example.imdb.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProfessionServiceImpl implements ProfessionService {

    private final ProfessionRepository professionRepository;

    @Autowired
    public ProfessionServiceImpl(ProfessionRepository professionRepository) {
        this.professionRepository = professionRepository;
    }

    @Override
    public void create(Profession profession) {
        this.professionRepository.save(profession);
    }

    @Override
    public void saveAll(List<Profession> professionList) {
        if (professionList != null && !professionList.isEmpty())
            this.professionRepository.saveAll(professionList);
    }

    @Override
    public Profession findById(Long id) {
        return this.professionRepository.findById(id).orElseThrow(()-> new RuntimeException("Profession not found : " + id));
    }

    @Override
    public Optional<Profession> fetchById(Long id) {
        return  this.professionRepository.findById(id);
    }

    @Override
    public void initializeProfessionsFromConstants() {
        List<Profession> professionList = new ArrayList<>();
        for (Map.Entry<Long, String> entry : Constants.PROFESSION.entrySet()) {
            Optional<Profession> professionOptional = this.fetchByName(entry.getValue());
            if(professionOptional.isEmpty()) {
                Profession profession = Profession.builder()
                        .id(entry.getKey())
                        .name(entry.getValue())
                        .build();
                professionList.add(profession);
            }
        }
        this.saveAll(professionList);
    }

    @Override
    public Profession findByName(String name) {
        return this.professionRepository.findByName(name).orElseThrow(()-> new RuntimeException("Profession not found : " + name));
    }

    @Override
    public Optional<Profession> fetchByName(String name) {
        return  this.professionRepository.findByName(name);
    }
}
