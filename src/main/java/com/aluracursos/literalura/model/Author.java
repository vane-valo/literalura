package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authorsName;
    private Integer authorsBirthYear;
    private Integer authorsDeathYear;
    @OneToMany(mappedBy = "bookAuthor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;

    public Author(){}

    public Author(AuthorsInfo authorsInfo) {
        this.authorsName = authorsInfo.authorsName();
        this.authorsBirthYear = authorsInfo.authorsBirthYear();
        this.authorsDeathYear = authorsInfo.authorsDeathYear();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public String getAuthorsName() {
        return authorsName;
    }

    public void setAuthorsName(String authorsName) {
        this.authorsName = authorsName;
    }

    public Integer getAuthorsBirthYear() {
        return authorsBirthYear;
    }

    public void setAuthorsBirthYear(Integer authorsBirthYear) {
        this.authorsBirthYear = authorsBirthYear;
    }

    public Integer getAuthorsDeathYear() {
        return authorsDeathYear;
    }

    public void setAuthorsDeathYear(Integer authorsDeathYear) {
        this.authorsDeathYear = authorsDeathYear;
    }

    @Override
    public String toString() {
        return "authorsName='" + authorsName + '\'' +
                ", authorsBirthYear=" + authorsBirthYear +
                ", authorsDeathYear=" + authorsDeathYear;
    }
}
