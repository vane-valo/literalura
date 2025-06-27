package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Author;
import com.aluracursos.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
