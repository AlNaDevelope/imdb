package org.example.imdb.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "movie_type")
public class MovieType extends BaseModel<Long> {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "type", fetch = FetchType.EAGER)
    private Set<Movie> movies;

}
