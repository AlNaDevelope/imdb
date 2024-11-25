package org.example.imdb.model;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder(toBuilder = true)
public class BaseModel<T> implements Serializable {

    @Id
    private T id;
}
