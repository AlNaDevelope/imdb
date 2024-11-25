package org.example.imdb.repository;

import org.example.imdb.model.Genre;
import org.example.imdb.model.MovieType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends BaseRepository<Genre, Long> {
    Optional<Genre> findByName(String name);

}
