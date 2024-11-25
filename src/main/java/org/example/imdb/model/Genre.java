package org.example.imdb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "genre")
@SuperBuilder(toBuilder = true)
public class Genre extends BaseModel<Long> {

    @Column(nullable = false, name = "name")
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies = new HashSet<>();
}
