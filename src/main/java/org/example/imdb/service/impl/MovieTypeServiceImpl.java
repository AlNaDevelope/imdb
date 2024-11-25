package org.example.imdb.service.impl;

import org.example.imdb.model.MovieType;
import org.example.imdb.model.Profession;
import org.example.imdb.repository.MovieTypeRepository;
import org.example.imdb.repository.ProfessionRepository;
import org.example.imdb.service.MovieTypeService;
import org.example.imdb.service.ProfessionService;
import org.example.imdb.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MovieTypeServiceImpl implements MovieTypeService {

    private final MovieTypeRepository movieTypeRepository;

    @Autowired
    public MovieTypeServiceImpl(MovieTypeRepository movieTypeRepository) {
        this.movieTypeRepository = movieTypeRepository;
    }

    @Override
    public void create(MovieType movieType) {
        this.movieTypeRepository.save(movieType);
    }

    @Override
    public void saveAll(List<MovieType> movieTypeList) {
        if(movieTypeList != null && !movieTypeList.isEmpty())
            this.movieTypeRepository.saveAll(movieTypeList);
    }

    @Override
    public MovieType findById(Long id) {
        return this.movieTypeRepository.findById(id).orElseThrow(()-> new RuntimeException("MovieType not found : " + id));
    }

    @Override
    public Optional<MovieType> fetchById(Long id) {
        return  this.movieTypeRepository.findById(id);
    }

    @Override
    public void initializeMovieTypesFromConstants() {
        List<MovieType> movieTypeList = new ArrayList<>();
        for (Map.Entry<Long, String> entry : Constants.MOVIE_TYPE.entrySet()) {
            Optional<MovieType> movieTypeOptional = this.fetchByName(entry.getValue());
            if(movieTypeOptional.isEmpty()) {
                MovieType movieType = MovieType.builder()
                        .id(entry.getKey())
                        .name(entry.getValue())
                        .build();
                movieTypeList.add(movieType);
            }
        }
        this.saveAll(movieTypeList);
    }

    @Override
    public MovieType findByName(String name) {
        return this.movieTypeRepository.findByName(name).orElseThrow(()-> new RuntimeException("MovieType not found : " + name));
    }

    @Override
    public Optional<MovieType> fetchByName(String name) {
        return this.movieTypeRepository.findByName(name);
    }
}
