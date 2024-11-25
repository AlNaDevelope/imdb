package org.example.imdb.repository;

import org.example.imdb.model.MovieRating;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRatingRepository extends BaseRepository<MovieRating, String> {
}
