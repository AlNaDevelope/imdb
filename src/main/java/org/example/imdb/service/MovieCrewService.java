package org.example.imdb.service;

import org.example.imdb.model.MovieCrew;

import java.io.IOException;
import java.util.List;

public interface MovieCrewService extends BaseService<MovieCrew, String> {

    void initializeMovieCrewFromCsv() throws IOException;
    List<MovieCrew> getMoviesWithSameDirectorAndWriter();

}
