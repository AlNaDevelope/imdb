package org.example.imdb.service.impl;

import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.imdb.model.Movie;
import org.example.imdb.model.MovieRating;
import org.example.imdb.repository.MovieRatingRepository;
import org.example.imdb.service.MovieRatingService;
import org.example.imdb.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieRatingServiceImpl implements MovieRatingService {

    public final MovieRatingRepository movieRatingRepository;
    public final MovieService movieService;

    @Autowired
    public MovieRatingServiceImpl(MovieRatingRepository movieRatingRepository, MovieService movieService) {
        this.movieRatingRepository = movieRatingRepository;
        this.movieService = movieService;
    }

    @Override
    @Transactional
    public void create(MovieRating movieRating) {
        this.movieRatingRepository.save(movieRating);
    }

    @Override
    @Transactional
    public void saveAll(List<MovieRating> movieRatingList) {
        if(movieRatingList != null && !movieRatingList.isEmpty())
            this.movieRatingRepository.saveAll(movieRatingList);
    }

    @Override
    public MovieRating findById(String id) {
        return this.movieRatingRepository.findById(id).orElseThrow(()-> new RuntimeException("MovieRating not found = " + id));
    }

    @Override
    public Optional<MovieRating> fetchById(String id) {
        return this.movieRatingRepository.findById(id);
    }

    @Override
    @Transactional
    public void initializeMovieRatingFromCsv() throws IOException {

        List<MovieRating> movieRatingList = new ArrayList<>();
        Reader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/csv/movieRating.csv")))
        );

        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord());
        int count=0;
        for (CSVRecord record : csvParser) {
            List<String> values = Arrays.asList(record.values());
            if(!values.isEmpty()){
                String id = values.get(0);
                Float averageRating = values.get(1).equals("\\N") ? null : Float.parseFloat(values.get(1));
                Integer numVotes = values.get(2).equals("\\N") ? null : Integer.parseInt(values.get(2));

                Movie movie = this.movieService.fetchById(id).orElse(null);

                MovieRating movieRating = MovieRating.builder()
                        .movie(movie)
                        .averageRating(averageRating)
                        .numVotes(numVotes)
                        .build();
                movieRatingList.add(movieRating);
//                logger.info("count = {}" , count);
                count++;
                if(count == 5000)
                    break;
            }
        }
        logger.info("count = {}" , count);
        this.saveAll(movieRatingList);
    }
    private static final Logger logger = LoggerFactory.getLogger(MovieRatingServiceImpl.class);

}
