package org.example.imdb.service;

import org.example.imdb.model.Artist;
import org.example.imdb.model.Movie;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface MovieService extends BaseService<Movie, String>{

    void initializeMovieFromCsv() throws IOException;
    List<Movie> findActors(String genre);
    Set<Movie> findTwoArtistInMovie(String firstArtistName, String secondArtistName);

}


