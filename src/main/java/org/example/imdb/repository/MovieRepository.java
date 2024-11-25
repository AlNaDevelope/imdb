package org.example.imdb.repository;

import org.example.imdb.model.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends BaseRepository<Movie, String> {


    @Query("SELECT m FROM Movie m " +
            "JOIN m.genres g " +
            "JOIN m.movieRating mr " +
            "WHERE g.name = :genre AND " +
            "mr.averageRating = (" +
            "    SELECT MAX(mr2.averageRating) " +
            "    FROM Movie m2 " +
            "    JOIN m2.genres g2 " +
            "    JOIN m2.movieRating mr2 " +
            "    WHERE g2.name = :genre AND m2.startYear = m.startYear" +
            ")")
    List<Movie> findActors(@Param("genre") String genre);
}
