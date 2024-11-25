package org.example.imdb.repository;

import org.example.imdb.model.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends BaseRepository<Artist, String> {

    Optional<Artist> findByPrimaryName(String name);
}
