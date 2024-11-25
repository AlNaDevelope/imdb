package org.example.imdb.service;

import org.example.imdb.model.MovieType;
import org.example.imdb.model.Profession;

import java.util.Optional;

public interface ProfessionService extends BaseService<Profession, Long> {

    void initializeProfessionsFromConstants();
    Profession findByName(String name);
    Optional<Profession> fetchByName(String name);

}
