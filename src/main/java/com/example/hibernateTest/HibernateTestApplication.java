package com.example.hibernateTest;

import com.example.hibernateTest.entities.LazyAuthor;
import com.example.hibernateTest.entities.Book;
import com.example.hibernateTest.repositories.LazyAuthorRepository;
import com.example.hibernateTest.repositories.BookRepository;
import com.example.hibernateTest.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Set;

@SpringBootApplication
public class HibernateTestApplication {

    @Autowired
    LazyAuthorRepository lazyAuthorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

    public static void main(String[] args) {
        SpringApplication.run(HibernateTestApplication.class, args);
    }

   // @Bean
    public void foo() {
        bookRepository.save(new Book("B1"));
        bookRepository.save(new Book("B2"));
        bookRepository.save(new Book("B3"));

        lazyAuthorRepository.save(new LazyAuthor("A1", Set.of(bookRepository.findById(1L).get())));
        lazyAuthorRepository.save(new LazyAuthor("" +
                "A2",
                Set.of(bookRepository.findById(1L).get())
        ));
        lazyAuthorRepository.save(new LazyAuthor(
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
        lazyAuthorRepository.findAll().forEach(System.out::println);
        System.out.println("------------END-----------");
    }

}
