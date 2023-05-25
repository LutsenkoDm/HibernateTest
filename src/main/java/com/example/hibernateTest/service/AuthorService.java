package com.example.hibernateTest.service;

import com.example.hibernateTest.entities.BatchedAuthor;
import com.example.hibernateTest.entities.EagerAuthor;
import com.example.hibernateTest.entities.LazyAuthor;
import com.example.hibernateTest.entities.Book;
import com.example.hibernateTest.repositories.BatchedAuthorRepository;
import com.example.hibernateTest.repositories.EagerAuthorRepository;
import com.example.hibernateTest.repositories.LazyAuthorRepository;
import com.example.hibernateTest.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

@Service
public class AuthorService {

    @Autowired
    LazyAuthorRepository lazyAuthorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    EagerAuthorRepository eagerAuthorRepository;

    @Autowired
    BatchedAuthorRepository batchedAuthorRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createThreeAuthors() {
        Book b1 = new Book("Book from transaction 1");
        bookRepository.save(b1);

        lazyAuthorRepository.save(new LazyAuthor("Author from transaction 1"));
        lazyAuthorRepository.save(new LazyAuthor("Author from transaction 2"));
        lazyAuthorRepository.save(new LazyAuthor("Author from transaction 3", Set.of(b1)));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = IOException.class)
    public void createThreeAuthorsWithFail() throws IOException {
        Book b1 = new Book("Book from transaction 11");
        bookRepository.save(b1);

        lazyAuthorRepository.save(new LazyAuthor("Author from transaction 11"));
        lazyAuthorRepository.save(new LazyAuthor("Author from transaction 21"));
        lazyAuthorRepository.save(new LazyAuthor("Author from transaction 31", Set.of(b1)));

        Files.delete(Path.of("/aaa"));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void create3WithLazyBooks() {
        System.out.println("-----------CREATE LAZY-----------");
        Book b1 = new Book("B1");
        Book b2 = new Book("B2");
        Book b3 = new Book("B3");
       /* Book b4 = new Book("B4");
        Book b5 = new Book("B5");*/

        bookRepository.save(b1);
        bookRepository.save(b2);
        bookRepository.save(b3);

        lazyAuthorRepository.save(new LazyAuthor("A1", Set.of(b1)));
        lazyAuthorRepository.save(new LazyAuthor("A2", Set.of(b2)));
        lazyAuthorRepository.save(new LazyAuthor("A3", Set.of(b3)));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void create3WithEagerBooks() {
        System.out.println("-----------CREATE EAGER-----------");
        Book b1 = new Book("B1");
        Book b2 = new Book("B2");
        Book b3 = new Book("B3");

        bookRepository.save(b1);
        bookRepository.save(b2);
        bookRepository.save(b3);

        eagerAuthorRepository.save(new EagerAuthor("A1", Set.of(b1)));
        eagerAuthorRepository.save(new EagerAuthor("A2", Set.of(b2)));
        eagerAuthorRepository.save(new EagerAuthor("A3", Set.of(b3)));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void create3WithBatchedBooks() {
        System.out.println("-----------CREATE BATCHED-----------");
        Book b1 = new Book("B1");
        Book b2 = new Book("B2");
        Book b3 = new Book("B3");

        bookRepository.save(b1);
        bookRepository.save(b2);
        bookRepository.save(b3);

        batchedAuthorRepository.save(new BatchedAuthor("A1", Set.of(b1)));
        batchedAuthorRepository.save(new BatchedAuthor("A2", Set.of(b2)));
        batchedAuthorRepository.save(new BatchedAuthor("A3", Set.of(b3)));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void getNameContaining(String str) {
        System.out.println("-----------GET NAME CONTAINING-----------");
        List<LazyAuthor> authorLazies = lazyAuthorRepository.findByAuthorNameContaining(str);
        authorLazies.forEach(System.out::println);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void getFirstLazy() {
        System.out.println("-----------GET LAZY FIRST-----------");
        LazyAuthor lazyAuthor = lazyAuthorRepository.findById(1L).get();
        System.out.println(lazyAuthor.getId());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void getFirstEager() {
        System.out.println("-----------GET EAGER FIRST-----------");
        EagerAuthor eagerAuthor = eagerAuthorRepository.findById(1L).get();
        System.out.println(eagerAuthor.getId());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void getFirstBatched() {
        System.out.println("-----------GET BATCHED FIRST-----------");
        BatchedAuthor eagerAuthor = batchedAuthorRepository.findById(1L).get();
        System.out.println(eagerAuthor.getId());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void getAllLazy() {
        System.out.println("-----------GET LAZY ALL-----------");
        lazyAuthorRepository.findAll().forEach(System.out::println);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void getAllEager() {
        System.out.println("-----------GET EAGER ALL-----------");
        eagerAuthorRepository.findAll().forEach(System.out::println);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void getAllBatched() {
        System.out.println("-----------GET BATCHED ALL-----------");
        batchedAuthorRepository.findAll().forEach(System.out::println);
    }

/*    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test2() {
        authorRepository.save(new Author("A4", Set.of(bookRepository.findById(1L).get())));
    }*/

}
