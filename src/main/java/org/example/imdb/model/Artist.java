package org.example.imdb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "artist")
public class Artist extends BaseModel<String>{

    @Column(name = "primary_name", nullable = false)
    private String primaryName;

    @Column(name ="birth_year")
    private Integer birthYear;

    @Column(name ="death_year")
    private Integer deathYear;

    @ManyToMany
    @JoinTable(
            name = "artist_profession",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name="profession_id")
    )
    private Set<Profession> professions;

    @ManyToMany
    @JoinTable(
            name = "artist_movie_title",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name="movie_id")
    )
    private Set<Movie> movieTitles;


    @ManyToMany(mappedBy = "directors", fetch = FetchType.EAGER)
    private Set<MovieCrew> directors;

    @ManyToMany(mappedBy = "writers", fetch = FetchType.EAGER)
    private Set<MovieCrew> writers;

}
