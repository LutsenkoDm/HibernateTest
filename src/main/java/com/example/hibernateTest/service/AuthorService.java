package com.example.hibernateTest.service;

import com.example.hibernateTest.entities.Author;
import com.example.hibernateTest.entities.Book;
import com.example.hibernateTest.repositories.AuthorRepository;
import com.example.hibernateTest.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createThreeAuthors() {
        Book b1 = new Book("Book from transaction 1");
        bookRepository.save(b1);

        authorRepository.save(new Author("Author from transaction 1"));
        authorRepository.save(new Author("Author from transaction 2"));
        authorRepository.save(new Author("Author from transaction 3", Set.of(b1)));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = IOException.class)
    public void createThreeAuthorsWithFail() throws IOException {
        Book b1 = new Book("Book from transaction 11");
        bookRepository.save(b1);

        authorRepository.save(new Author("Author from transaction 11"));
        authorRepository.save(new Author("Author from transaction 21"));
        authorRepository.save(new Author("Author from transaction 31", Set.of(b1)));

        Files.delete(Path.of("/aaa"));
    }
}
