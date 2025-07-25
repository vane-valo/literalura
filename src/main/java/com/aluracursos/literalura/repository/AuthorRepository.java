package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByAuthorsName(String authorsName);

    @Query("SELECT a FROM Author a WHERE a.authorsBirthYear <= :year AND a.authorsDeathYear >= :year")
    List<Author> authorsAliveInSpecificYear(Integer year);

}
