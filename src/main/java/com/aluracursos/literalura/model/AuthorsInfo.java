package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorsInfo(
        @JsonAlias("name") String authorsName,
        @JsonAlias("birth_year") Integer authorsBirthYear,
        @JsonAlias("death_year") Integer authorsDeathYear
) {
}
