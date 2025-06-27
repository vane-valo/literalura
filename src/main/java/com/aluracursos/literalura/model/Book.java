package com.aluracursos.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String bookTitle;
    @OneToMany()
    private Author bookAuthor;
    private String bookLanguage;
    private Integer bookDownloadCount;

    public Book(){}

    public Book(BookInfo bookInfo, Author author) {
        this.bookTitle = bookInfo.bookTitle();
        this.bookAuthor = author;
        this.bookLanguage = bookInfo.bookLanguage().getFirst();
        this.bookDownloadCount = bookInfo.bookDownloadCount();
    }

    public Long getId() {
        return id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public Author getBookAuthor() {
        return bookAuthor;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public Integer getBookDownloadCount() {
        return bookDownloadCount;
    }

    @Override
    public String toString() {
        return "bookTitle='" + bookTitle + '\'' +
                ", bookAuthor=" + bookAuthor +
                ", bookLanguage=" + bookLanguage +
                ", bookDownloadCount=" + bookDownloadCount;
    }
}
