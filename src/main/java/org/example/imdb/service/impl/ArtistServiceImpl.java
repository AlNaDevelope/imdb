package org.example.imdb.service.impl;

import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.imdb.model.Artist;
import org.example.imdb.model.Movie;
import org.example.imdb.model.Profession;
import org.example.imdb.repository.ArtistRepository;
import org.example.imdb.service.ArtistService;
import org.example.imdb.service.MovieService;
import org.example.imdb.service.ProfessionService;
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

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ProfessionService professionService;
    private final MovieService movieService;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository, ProfessionService professionService,@Lazy MovieService movieService) {
        this.artistRepository = artistRepository;
        this.professionService = professionService;
        this.movieService = movieService;
    }

    @Override
    @Transactional
    public void create(Artist artist) {
        this.artistRepository.save(artist);
    }

    @Override
    @Transactional
    public void saveAll(List<Artist> artistList) {
        if (artistList != null && !artistList.isEmpty())
            this.artistRepository.saveAll(artistList);
    }


    @Override
    public Artist findById(String id) {
        return this.artistRepository.findById(id).orElseThrow(()-> new RuntimeException("Artist not found : " + id));
    }

    @Override
    public Optional<Artist> fetchById(String id) {
        return this.artistRepository.findById(id);
    }

    @Override
    @Transactional
    public void initializeArtistFromCsv() throws IOException {

        List<Artist> artistList = new ArrayList<>();

        Reader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/csv/artist.csv")))
        );

        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord());

        Map<String, Integer> map = csvParser.getHeaderMap();
        logger.info("csvParser.getHeaderMap() = {}", map);
        int count = 0;
        for (CSVRecord record : csvParser) {

            List<String> values = Arrays.asList(record.values());
            if(!values.isEmpty()){

                String id = values.get(0);
                String primaryName = values.get(1);
                Integer birthYear = values.get(2).equals("\\N") ? null : Integer.parseInt(values.get(2));
                Integer deathYear = values.get(3).equals("\\N") ? null : Integer.parseInt(values.get(3));
                List<String> professionListStr = values.get(4).isEmpty() && values.get(4).equals("\\N") ? new ArrayList<>() : Arrays.asList(values.get(4).split(","));
                List<String> movieIdStr = values.get(5).isEmpty() && values.get(5).equals("\\N") ? new ArrayList<>() : Arrays.stream(values.get(5).split(",")).toList();

                Set<Profession> professionList = new HashSet<>();
                for(String profession : professionListStr){
                    this.professionService.fetchByName(profession)
                            .ifPresent(professionList::add);
                }

                Set<Movie> movieSet = new HashSet<>();
                for(String movieId : movieIdStr){
                    this.movieService.fetchById(movieId)
                            .ifPresent(movieSet::add);
                }

                Artist artist = Artist.builder().id(id).primaryName(primaryName).birthYear(birthYear).deathYear(deathYear).professions(professionList).movieTitles(movieSet).build();
                artistList.add(artist);
//                logger.info("count = {}" , count);
                count++;
                if(count == 5000)
                    break;
            }

        }
        logger.info("count = {}" , count);
        this.saveAll(artistList);

    }

    @Override
    public Artist findByPrimaryName(String name) {
        return this.artistRepository.findByPrimaryName(name).orElseThrow(()-> new RuntimeException("Artist not found : " + name));
    }
    @Override
    public Optional<Artist> fetchByPrimaryName(String name) {
        return this.artistRepository.findByPrimaryName(name);
    }


    private static final Logger logger = LoggerFactory.getLogger(ArtistServiceImpl.class);
}
