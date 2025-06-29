package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AuthorRepository;
import com.aluracursos.literalura.repository.BookRepository;
import com.aluracursos.literalura.service.ConverterAPI;
import com.aluracursos.literalura.service.RequestAPI;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principal {
    Scanner scanner = new Scanner(System.in);
    private RequestAPI requestAPI = new RequestAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConverterAPI converterAPI = new ConverterAPI();
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private String json;
    List<Book> bookList;
    List<Author> authorList;

    public Principal(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void menuApp(){
        int option = -1;

        while (option != 0){
            String menu = """
                    1 - Search book by title
                    2 - List of books searched
                    3 - List of authors registered
                    4 - List of authors alive from a specific year
                    5 - Find books by language
                    0 - Log out""";
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1 -> searchBookByTitle();
                case 2 -> findAllBooks();
                case 3 -> findAllAuthors();
                case 4 -> findAuthorsAliveInSpecificYear();
                case 5 -> findBooksByLanguage();
                case 0 -> System.out.println("Logged out");
                default -> System.out.println("Invalid option");
            }
        }
    }

    private BookInfo getBooksFromApi(){
        System.out.println("What book do you want?");
        var bookTitle = scanner.nextLine();
        json = requestAPI.getApiData(URL_BASE +
                "?search="
                + bookTitle.replace(" ", "%20"));
        //System.out.println(json);
        BookResult info = converterAPI.getApiData(json, BookResult.class);
        Optional<BookInfo> bookSearched = info.bookResult().stream()
                .filter(b -> b.bookTitle().toLowerCase().contains(bookTitle.toLowerCase()))
                .findFirst();

        //System.out.println(info);
        //System.out.println(bookSearched);

        if (bookSearched.isPresent()){
            System.out.println("The book searched is: ");
            System.out.println(bookSearched.get());
            return bookSearched.get();
        } else {
            System.out.println("Book not found");
            return null;
        }
    }

    private void searchBookByTitle() {
        BookInfo bookInfo = getBooksFromApi();
        if (bookInfo != null){
            Book book;
            AuthorsInfo authorsInfo = bookInfo.bookAuthor().getFirst();
            Author authorInDatabase = authorRepository.findByAuthorsName(authorsInfo.authorsName());
            //System.out.println("Author: " + authorInDatabase);

            if (authorInDatabase != null){
                book = new Book(bookInfo, authorInDatabase);
            } else {
                Author newAuthor = new Author(authorsInfo);
                book = new Book(bookInfo, newAuthor);
                authorRepository.save(newAuthor);
            }
            try {
                bookRepository.save(book);
                //System.out.println(book);
            } catch (Exception e){
                System.out.println("The book is already in registered: " + e);
            }
        }
    }

    private void findAllBooks(){
        bookList = bookRepository.findAll();
        bookList.forEach(b ->
                System.out.println("\n-----BOOK-----" +
                        "\nTitle: " + b.getBookTitle() +
                        "\nAuthor: " + b.getBookAuthor().getAuthorsName() +
                        "\nLanguage: " + b.getBookLanguage() +
                        "\nTotal downloads: " + b.getBookDownloadCount() +
                        "\n--------------"));
    }

    private void findAllAuthors(){
        authorList = authorRepository.findAll();
        authorList.forEach(a ->
                System.out.println("\nAuthor: " + a.getAuthorsName() +
                        "\nYear of birth: " + a.getAuthorsBirthYear() +
                        "\nYear of death: " + a.getAuthorsDeathYear() +
                        "\nBooks: " + a.getBooks().stream()
                        .map(b -> b.getBookTitle()).collect(Collectors.toList())));
    }

    private void findAuthorsAliveInSpecificYear(){
        System.out.println("Write the year: ");
        Integer year = scanner.nextInt();

        authorList = authorRepository.authorsAliveInSpecificYear(year);
        authorList.forEach(a ->
                System.out.println("\nAuthor: " + a.getAuthorsName() +
                        "\nYear of birth: " + a.getAuthorsBirthYear() +
                        "\nYear of death: " + a.getAuthorsDeathYear() +
                        "\nBooks: " + a.getBooks().stream()
                        .map(b -> b.getBookTitle()).collect(Collectors.toList())));
    }

    private void findBooksByLanguage(){
        String languageOptions = """
                es - Spanish
                en - English
                fr - French
                pt - Portuguese""";
        System.out.println("Write the language by this options: ");
        System.out.println(languageOptions);
        String language = scanner.next();

        if (Stream.of("es", "en", "pt", "fr").anyMatch(s -> language.toLowerCase().equals(s))){
            bookList = bookRepository.booksByLanguage(language.toLowerCase());
            bookList.forEach(b ->
                    System.out.println("\n-----BOOK-----" +
                            "\nTitle: " + b.getBookTitle() +
                            "\nAuthor: " + b.getBookAuthor().getAuthorsName() +
                            "\nLanguage: " + b.getBookLanguage() +
                            "\nTotal downloads: " + b.getBookDownloadCount() +
                            "\n--------------"));
        } else {
            System.out.println("Language not found.");
        }
    }

}
