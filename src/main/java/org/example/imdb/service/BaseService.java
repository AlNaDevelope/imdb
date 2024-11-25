package org.example.imdb.service;


import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {

    void create(T model);
    void saveAll(List<T> models);
    T findById(ID id);
    Optional<T> fetchById(ID id);

}
