package org.example.imdb.repository;


import org.example.imdb.model.Profession;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessionRepository extends BaseRepository<Profession, Long>{

    Optional<Profession> findByName(String name);

}
