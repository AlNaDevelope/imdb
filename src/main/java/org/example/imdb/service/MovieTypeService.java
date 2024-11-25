package org.example.imdb.service;

import org.example.imdb.model.MovieType;

import java.util.Optional;

public interface MovieTypeService extends BaseService<MovieType, Long> {

    void initializeMovieTypesFromConstants();
    MovieType findByName(String name);
    Optional<MovieType> fetchByName(String name);
}
