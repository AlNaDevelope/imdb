package org.example.imdb.repository;


import org.example.imdb.model.MovieType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieTypeRepository extends BaseRepository<MovieType, Long>{

    Optional<MovieType> findByName(String name);
}
