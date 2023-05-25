package com.example.hibernateTest.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "lazy_author")
public class LazyAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "author_name")
    private String authorName;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Book> books;

    public LazyAuthor(String authorName) {
        this.authorName = authorName;
    }

    public LazyAuthor(String authorName, Set<Book> books) {
        this.authorName = authorName;
        this.books = books;
    }
}