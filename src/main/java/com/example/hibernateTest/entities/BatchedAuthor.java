package com.example.hibernateTest.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "batched_author")
public class BatchedAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "author_name")
    private String authorName;

    @OneToMany(fetch = FetchType.LAZY)
    @BatchSize(size=2)
    private Set<Book> books;

    public BatchedAuthor(String authorName) {
        this.authorName = authorName;
    }

    public BatchedAuthor(String authorName, Set<Book> books) {
        this.authorName = authorName;
        this.books = books;
    }
}