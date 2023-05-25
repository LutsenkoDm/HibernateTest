package com.example.hibernateTest.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "eager_author")
public class EagerAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "author_name")
    private String authorName;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Book> books;

    public EagerAuthor(String authorName) {
        this.authorName = authorName;
    }

    public EagerAuthor(String authorName, Set<Book> books) {
        this.authorName = authorName;
        this.books = books;
    }
}