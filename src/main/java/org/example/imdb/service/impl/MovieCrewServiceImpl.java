package org.example.imdb.service.impl;

import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.imdb.model.Artist;
import org.example.imdb.model.Movie;
import org.example.imdb.model.MovieCrew;
import org.example.imdb.model.MovieRating;
import org.example.imdb.repository.MovieCrewRepository;
import org.example.imdb.service.ArtistService;
import org.example.imdb.service.MovieCrewService;
import org.example.imdb.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;


@Service
public class MovieCrewServiceImpl implements MovieCrewService {

    public final MovieCrewRepository movieCrewRepository;
    public final MovieService movieService;
    public final ArtistService artistService;

    public MovieCrewServiceImpl(MovieCrewRepository movieCrewRepository, MovieService movieService, ArtistService artistService) {
        this.movieCrewRepository = movieCrewRepository;
        this.movieService = movieService;
        this.artistService = artistService;
    }


    @Override
    @Transactional
    public void create(MovieCrew movieCrew) {
        this.movieCrewRepository.save(movieCrew);
    }

    @Override
    @Transactional
    public void saveAll(List<MovieCrew> movieCrewList) {
        if(movieCrewList != null && !movieCrewList.isEmpty())
            this.movieCrewRepository.saveAll(movieCrewList);
    }

    @Override
    public MovieCrew findById(String id) {
        return this.movieCrewRepository.findById(id).orElseThrow(()-> new RuntimeException("MovieCrew not found = " + id));
    }

    @Override
    public Optional<MovieCrew> fetchById(String id) {
        return this.movieCrewRepository.findById(id);
    }


    @Override
    @Transactional
    public void initializeMovieCrewFromCsv() throws IOException {

        List<MovieCrew> movieCrewList = new ArrayList<>();
        Reader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/csv/movieCrew.csv")))
        );

        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord());
        int count=0;
        for (CSVRecord record : csvParser) {
            List<String> values = Arrays.asList(record.values());
            if(!values.isEmpty()){
                String id = values.get(0).equals("\\N") ? null : values.get(0);
                List<String> directorStrList = values.get(1).equals("\\N") ? new ArrayList<>() : Arrays.stream(values.get(1).split(",")).toList();
                List<String> writerStrList = values.get(2).equals("\\N") ? new ArrayList<>() : Arrays.stream(values.get(2).split(",")).toList();

                Movie movie = this.movieService.fetchById(id).orElse(null);
                if(movie != null){
                    Set<Artist> direcotrSet = new HashSet<>();
                    for(String director : directorStrList){
                        this.artistService.fetchById(director).ifPresent(direcotrSet::add);

                    }
                    Set<Artist> writerSet = new HashSet<>();
                    for(String writer : writerStrList){
                        this.artistService.fetchById(writer).ifPresent(writerSet::add);

                    }
                    MovieCrew movieCrew = MovieCrew.builder()
                            .movie(movie)
                            .directors(direcotrSet)
                            .writers(writerSet)
                            .build();

                    movieCrewList.add(movieCrew);

                }
//                logger.info("count = {}" , count);
                count++;
                if(count == 5000)
                    break;

            }
        }
        logger.info("count = {}" , count);
        this.saveAll(movieCrewList);

    }

    @Override
    public List<MovieCrew> getMoviesWithSameDirectorAndWriter() {
        return this.movieCrewRepository.findMoviesWithSameDirectorAndWriter();
    }

    private static final Logger logger = LoggerFactory.getLogger(MovieCrewServiceImpl.class);

}
