package org.example.imdb.service.impl;

import org.example.imdb.model.Genre;
import org.example.imdb.model.MovieCrew;
import org.example.imdb.model.MovieType;
import org.example.imdb.repository.GenreRepository;
import org.example.imdb.service.GenreService;
import org.example.imdb.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void create(Genre genre) {
        this.genreRepository.save(genre);
    }

    @Override
    public void saveAll(List<Genre> genres) {
        if(genres != null && !genres.isEmpty())
            this.genreRepository.saveAll(genres);
    }

    @Override
    public Genre findById(Long id) {
        return this.genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found for id: " + id));
    }

    @Override
    public Optional<Genre> fetchById(Long id) {
        return this.genreRepository.findById(id);
    }

    @Override
    public Genre findByName(String name) {
        return this.genreRepository.findByName(name).orElseThrow(()-> new RuntimeException("MovieType not found : " + name));
    }

    @Override
    public Optional<Genre> fetchByName(String name) {
        return this.genreRepository.findByName(name);
    }

    @Override
    public void initializeGenresFromConstants (){
        List<Genre> genreList = new ArrayList<>();
        for (Map.Entry<Long, String> entry : Constants.GENRE.entrySet()) {
            Optional<Genre> existingGenre = this.fetchByName(entry.getValue());
            if(existingGenre.isEmpty()) {
                Genre genre = Genre.builder()
                        .id(entry.getKey())
                        .name(entry.getValue())
                        .build();
                genreList.add(genre);

            }
        }
        this.saveAll(genreList);
    }

    private static final Logger logger = LoggerFactory.getLogger(GenreServiceImpl.class);

}
