package com.example.hibernateTest.repositories;

import com.example.hibernateTest.entities.BatchedAuthor;
import com.example.hibernateTest.entities.EagerAuthor;
import com.example.hibernateTest.entities.LazyAuthor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatchedAuthorRepository extends JpaRepository<BatchedAuthor, Long> {
    List<BatchedAuthor> findByAuthorNameContaining(String authorName);;
}

/*
Hibernate:
    select
        b1_0.id,
        b1_0.author_name,
        b2_0.batched_author_id,
        b2_1.id,
        b2_1.book_name
    from
        batched_author b1_0
    left join
        (batched_author_books b2_0
    join
        book b2_1
            on b2_1.id=b2_0.books_id)
                on b1_0.id=b2_0.batched_author_id

Hibernate:
    select
        l1_0.id,
        l1_0.author_name,
        b1_0.lazy_author_id,
        b1_1.id,
        b1_1.book_name
    from
        lazy_author l1_0
    left join
        (lazy_author_books b1_0
    join
        book b1_1
            on b1_1.id=b1_0.books_id)
                on l1_0.id=b1_0.lazy_author_id
 */