package com.aluracursos.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AuthorDTO(Long id,
                        String authorsName,
                        Integer authorsBirthYear,
                        Integer authorsDeathYear) {
}
