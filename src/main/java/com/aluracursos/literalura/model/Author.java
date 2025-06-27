package com.aluracursos.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authorsName;
    private Integer authorsBirthYear;
    private Integer authorsDeathYear;

    public Author(){}

    public Author(AuthorsInfo authorsInfo) {
        this.authorsName = authorsInfo.authorsName();
        this.authorsBirthYear = authorsInfo.authorsBirthYear();
        this.authorsDeathYear = authorsInfo.authorsDeathYear();
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
