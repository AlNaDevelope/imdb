package org.example.imdb.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name = "movie")
public class Movie extends BaseModel<String>{


    @Column(name = "primary_title", nullable = false)
    private String primaryTitle;

    @Column(name = "original_title", nullable = false)
    private String originalTitle;

    @Column(name = "start_year")
    private Integer startYear;

    @Column(name = "end_year")
    private Integer endYear;

    @Column(name = "runtime_minutes")
    private Integer runtimeMinutes;



    @ManyToOne
    @JoinColumn(name = "movie_type", referencedColumnName = "id")
    private MovieType type;

    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name="movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @ManyToMany(mappedBy = "movieTitles")
    private Set<Artist> artists;

    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private MovieRating movieRating;

    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private MovieCrew movieCrew;


}
