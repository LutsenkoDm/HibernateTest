package com.example.hibernateTest;

import com.example.hibernateTest.entities.Author;
import com.example.hibernateTest.entities.Book;
import com.example.hibernateTest.repositories.AuthorRepository;
import com.example.hibernateTest.repositories.BookRepository;
import com.example.hibernateTest.service.AuthorService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class HibernateTestApplication {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;
    public static void main(String[] args) {
        SpringApplication.run(HibernateTestApplication.class, args);
    }

    @Bean
    public void foo() {
        bookRepository.save(new Book("B1"));
        bookRepository.save(new Book("B2"));
        bookRepository.save(new Book("B3"));

        authorRepository.save(new Author("A1", Set.of(bookRepository.findById(1L).get())));
        authorRepository.save(new Author("" +
                "A2",
                Set.of(bookRepository.findById(1L).get())
        ));
        authorRepository.save(new Author(
                "A3",
                Set.of(bookRepository.findById(2L).get(), bookRepository.findById(3L).get())
        ));

        authorService.createThreeAuthors();

        try {
            authorService.createThreeAuthorsWithFail();
        } catch (IOException e) {
        }

        System.out.println("------------Books-----------");
        bookRepository.findAll().forEach(System.out::println);
        System.out.println("------------Authors-----------");
        authorRepository.findAll().forEach(System.out::println);
        System.out.println("------------END-----------");
    }

}
