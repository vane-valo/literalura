package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookInfo(
        @JsonAlias("title") String bookTitle,
        @JsonAlias("authors") List<AuthorsInfo> bookAuthor,
        @JsonAlias("languages") List<String> bookLanguage,
        @JsonAlias("download_count") Integer bookDownloadCount
        ) {
}
