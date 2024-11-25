package org.example.imdb.config;

import org.example.imdb.model.Artist;
import org.example.imdb.service.ArtistService;
import org.example.imdb.service.GenreService;
import org.example.imdb.service.MovieCrewService;
import org.example.imdb.service.MovieRatingService;
import org.example.imdb.service.MovieService;
import org.example.imdb.service.MovieTypeService;
import org.example.imdb.service.ProfessionService;
import org.example.imdb.service.impl.MovieServiceImpl;
import org.example.imdb.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer implements CommandLineRunner{

    private final MovieService movieService;
    private final ArtistService artistService;
    private final MovieRatingService movieRatingService;
    private final MovieCrewService movieCrewService;
    private final GenreService genreService;
    private final ProfessionService professionService;
    private final MovieTypeService movieTypeService;


    @Autowired
    public DataInitializer(MovieService movieService, ArtistService artistService, MovieRatingService movieRatingService, MovieCrewService movieCrewService, GenreService genreService, ProfessionService professionService, MovieTypeService movieTypeService) {
        this.movieRatingService = movieRatingService;
        this.movieCrewService = movieCrewService;
        this.genreService = genreService;
        this.professionService = professionService;
        this.movieTypeService = movieTypeService;
        this.movieService = movieService;
        this.artistService = artistService;
    }

    @Override
    public void run(String... args) throws Exception {

        // add genre List top Data Base
        logger.info("Start Genre ####################");
        this.genreService.initializeGenresFromConstants();
        logger.info("End Genre ####################");

        // add profession List top Data Base
        logger.info("Start profession ####################");
        this.professionService.initializeProfessionsFromConstants();
        logger.info("End profession ####################");

        // add movie type List top Data Base
        logger.info("Start movie type ####################");
        this.movieTypeService.initializeMovieTypesFromConstants();
        logger.info("End movie type ####################");

        // add movie in csv
        logger.info("Start movie ####################");
        this.movieService.initializeMovieFromCsv();
        logger.info("End movie ####################");

        // add artist in csv
        logger.info("Start artist ####################");
        this.artistService.initializeArtistFromCsv();
        logger.info("End artist ####################");


        // add rating in csv
        logger.info("Start movie rating ####################");
//        this.movieRatingService.initializeMovieRatingFromCsv();
        logger.info("End movie rating ####################");

        // add movie crew in csv
        logger.info("Start movie crew ####################");
//        this.movieCrewService.initializeMovieCrewFromCsv();
        logger.info("End movie crew ####################");

        logger.info("EDN####################");

    }


    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

}
