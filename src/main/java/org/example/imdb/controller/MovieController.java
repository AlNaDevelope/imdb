package org.example.imdb.controller;


import org.example.imdb.model.Movie;
import org.example.imdb.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/movie")
public class MovieController {

    public final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping("/actors")
    @GetMapping
    public String getMoviesWithSameDirectorWriter(@RequestParam("genre") String genre) {

        List<Movie> movieList = this.movieService.findActors(genre);
        logger.info( " size  = {}" , movieList.size());
        StringBuilder name = new StringBuilder();
        for(Movie movie : movieList) {
            if(movie !=  null)
                name.append(movie.getId()).append("=").append(movie.getPrimaryTitle()).append(" = ").append(movie.getMovieRating().getAverageRating()).append(" = ").append(movie.getStartYear()).append("\n");
        }
        return name.toString();
    }

    @RequestMapping("/getMoviesWithSameDirectorWriter")
    @GetMapping
    public String getMoviesWithSameDirectorWriter(@RequestParam("firstArtist") String firstArtist, @RequestParam("secondArtist") String secondArtist) {

        Set<Movie> movieList = this.movieService.findTwoArtistInMovie(firstArtist, secondArtist);
        logger.info( " size  = {}" , movieList.size());
        StringBuilder name = new StringBuilder();
        for(Movie movie : movieList) {
            if(movie !=  null)
                name.append(movie.getId()).append("=").append(movie.getPrimaryTitle()).append(" = ").append(firstArtist).append(", ").append(secondArtist).append("\n");
        }
        return name.toString();
    }
    @RequestMapping("/t")
    @GetMapping
    public String tt() {

        return "dsgdf";
    }


    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

}
