package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AuthorRepository;
import com.aluracursos.literalura.repository.BookRepository;
import com.aluracursos.literalura.service.ConverterAPI;
import com.aluracursos.literalura.service.RequestAPI;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    Scanner scanner = new Scanner(System.in);
    private RequestAPI requestAPI = new RequestAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConverterAPI converterAPI = new ConverterAPI();
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
private String json;
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
        System.out.println(json);
        BookResult info = converterAPI.getApiData(json, BookResult.class);
        Optional<BookInfo> bookSearched = info.bookResult().stream()
                .filter(b -> b.bookTitle().toLowerCase().contains(bookTitle.toLowerCase()))
                .findFirst();

        System.out.println(info);
        System.out.println(bookSearched);

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
            System.out.println(authorInDatabase);

            if (authorInDatabase != null){
                book = new Book(bookInfo, authorInDatabase);
            } else {
                Author newAuthor = new Author(authorsInfo);
                book = new Book(bookInfo, newAuthor);
                authorRepository.save(newAuthor);
            }
            try {
                bookRepository.save(book);
                System.out.println(book);
            } catch (Exception e){
                System.out.println("The book is already in registered: " + e);
            }
        } else {
            System.out.println("The book is not found on Gutendex");
        }
    }
}
