package org.example.imdb.service.impl;

import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.imdb.model.Artist;
import org.example.imdb.model.Genre;
import org.example.imdb.model.Movie;
import org.example.imdb.model.MovieType;
import org.example.imdb.repository.MovieRepository;
import org.example.imdb.service.ArtistService;
import org.example.imdb.service.GenreService;
import org.example.imdb.service.MovieService;
import org.example.imdb.service.MovieTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieTypeService movieTypeService;
    private final GenreService genreService;
    private final ArtistService artistService;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieTypeService movieTypeService, GenreService genreService, @Lazy ArtistService artistService) {
        this.movieRepository = movieRepository;
        this.movieTypeService = movieTypeService;
        this.genreService = genreService;
        this.artistService = artistService;
    }

    @Override
    @Transactional
    public void create(Movie movie) {
        this.movieRepository.save(movie);
    }

    @Override
    @Transactional
    public void saveAll(List<Movie> movieList) {
        if (movieList != null && !movieList.isEmpty())
            this.movieRepository.saveAll(movieList);
    }


    @Override
    public Movie findById(String id) {
        return this.movieRepository.findById(id).orElseThrow(()-> new RuntimeException("Movie not found : " + id));
    }

    @Override
    public Optional<Movie> fetchById(String id) {
        return this.movieRepository.findById(id);
    }

    @Override
    @Transactional
    public void initializeMovieFromCsv() throws IOException {

        List<Movie> movieList = new ArrayList<>();
        Reader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/csv/Movie.csv")))
        );

        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord());

        Map<String, Integer> map = csvParser.getHeaderMap();
        logger.info("csvParser.getHeaderMap() = {}", map);
        int count = 0 ;
        for (CSVRecord record : csvParser) {

            List<String> values = Arrays.asList(record.values());
            if(!values.isEmpty()){

                String id = values.get(0);
                String type = values.get(1);
                String primaryTitle = values.get(2);
                String originalTitle = values.get(3);
                Integer startYear = values.get(4).equals("\\N") ? null : Integer.parseInt(values.get(4));
                Integer endYear = values.get(5).equals("\\N") ? null : Integer.parseInt(values.get(5));
                Integer runTime = values.get(6).equals("\\N") ? null : Integer.parseInt(values.get(6));
                List<String> genreStrList = values.get(7).isEmpty() && values.get(7).equals("\\N") ? new ArrayList<>() : Arrays.asList(values.get(7).split(","));

                Set<Genre> genreList = new HashSet<>();
                for(String genreStr : genreStrList){
                    this.genreService.fetchByName(genreStr)
                            .ifPresent(genreList::add);
                }

                MovieType movieType = this.movieTypeService.fetchByName(type).orElse(null);
                Movie movie = Movie.builder()
                        .id(id)
                        .type(movieType)
                        .primaryTitle(primaryTitle)
                        .originalTitle(originalTitle)
                        .startYear(startYear)
                        .endYear(endYear)
                        .runtimeMinutes(runTime)
                        .genres(genreList)
                        .build();
                movieList.add(movie);
//                logger.info("count = {}" , count);
                count++;

                if(count == 5000)
                    break;
            }
        }
        logger.info("count = {}" , count);
        this.saveAll(movieList);
    }

    @Override
    public List<Movie> findActors(String genre) {
        return this.movieRepository.findActors(genre);
    }
    @Override
    public Set<Movie> findTwoArtistInMovie(String firstArtistName, String secondArtistName) {

        Artist firstArtist = this.artistService.findByPrimaryName(firstArtistName);
        Artist secondArtist = this.artistService.findByPrimaryName(secondArtistName);

        Set<Movie> firstArtistMovies = firstArtist.getMovieTitles();
        Set<Movie> secondArtistMovies = secondArtist.getMovieTitles();

        if (firstArtistMovies == null || secondArtistMovies == null) {
            return new HashSet<>();
        }
        firstArtistMovies.retainAll(secondArtistMovies);

        return firstArtistMovies;
    }


    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);
}
