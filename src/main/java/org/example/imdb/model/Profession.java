package org.example.imdb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
@Table(name = "profession")
public class Profession extends BaseModel<Long>{

    @Column(unique=true, nullable=false, name = "name")
    private String name;

    @ManyToMany(mappedBy = "professions")
    private Set<Artist> artists;

}
