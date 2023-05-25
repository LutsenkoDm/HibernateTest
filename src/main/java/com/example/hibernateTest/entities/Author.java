package com.example.hibernateTest.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_name")
    private String authorName;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "author id")
    private Set<Book> books;

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public Author(String authorName, Set<Book> books) {
        this.authorName = authorName;
        this.books = books;
    }
}