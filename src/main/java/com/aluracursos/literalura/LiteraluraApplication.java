package com.aluracursos.literalura;

import com.aluracursos.literalura.principal.Principal;
import com.aluracursos.literalura.repository.AuthorRepository;
import com.aluracursos.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner{

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;
	public static void main(String[] args) {SpringApplication.run(LiteraluraApplication.class, args);}

	@Override
	public void run(String... args) throws Exception{
		Principal principal = new Principal(bookRepository, authorRepository);
		principal.menuApp();
	}

}
