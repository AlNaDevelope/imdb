package org.example.imdb.repository;

import org.example.imdb.model.MovieCrew;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieCrewRepository extends BaseRepository<MovieCrew, String> {

    @Query("SELECT mc FROM MovieCrew mc " +
            "JOIN mc.directors d " +
            "JOIN mc.writers w " +
            "WHERE d = w")
    List<MovieCrew> findMoviesWithSameDirectorAndWriter();

}
