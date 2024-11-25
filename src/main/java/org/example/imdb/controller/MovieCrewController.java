package org.example.imdb.controller;


import org.apache.commons.lang3.Validate;
import org.example.imdb.model.MovieCrew;
import org.example.imdb.service.MovieCrewService;
import org.example.imdb.service.impl.MovieServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/movie-crew")
public class MovieCrewController {


    public final MovieCrewService movieCrewService;

    public MovieCrewController(MovieCrewService movieCrewService) {
        this.movieCrewService = movieCrewService;
    }

    @RequestMapping("/moviesWithSameDirectorAndWriter")
    @GetMapping
    public String getMoviesWithSameDirectorWriter() {

        List<MovieCrew> movieCrewList = this.movieCrewService.getMoviesWithSameDirectorAndWriter();
        logger.info( " size  = {}" , movieCrewList.size());
        StringBuilder name = new StringBuilder();
        for(MovieCrew movieCrew : movieCrewList) {
            if(movieCrew.getMovie() !=  null)
                name.append(movieCrew.getMovie().getPrimaryTitle()).append(" , ");
        }
        return name.toString();
    }
    private static final Logger logger = LoggerFactory.getLogger(MovieCrewController.class);

}
