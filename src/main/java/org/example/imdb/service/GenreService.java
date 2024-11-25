package org.example.imdb.service;

import org.example.imdb.model.Genre;
import org.example.imdb.model.MovieType;

import java.util.Optional;

public interface GenreService  extends BaseService<Genre, Long> {

    void initializeGenresFromConstants();
    Genre findByName(String name);
    Optional<Genre> fetchByName(String name);


}
