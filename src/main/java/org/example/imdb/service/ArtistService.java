package org.example.imdb.service;

import org.example.imdb.model.Artist;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ArtistService extends BaseService<Artist, String> {

    void initializeArtistFromCsv() throws IOException;
    Artist findByPrimaryName(String name);
    Optional<Artist> fetchByPrimaryName(String name);

}
