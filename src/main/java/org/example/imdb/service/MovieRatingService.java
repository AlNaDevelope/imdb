package org.example.imdb.service;

import org.example.imdb.model.MovieRating;

import java.io.IOException;

public interface MovieRatingService extends BaseService<MovieRating, String> {

    void initializeMovieRatingFromCsv() throws IOException;
}
