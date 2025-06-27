package com.aluracursos.literalura.dto;

import com.aluracursos.literalura.model.AuthorsInfo;

import java.util.List;

public record BookDTO(Long id,
                      String bookTitle,
                      List<AuthorsInfo> bookAuthor,
                      List<String> bookLanguage,
                      Integer bookDownloadCount) {
}
