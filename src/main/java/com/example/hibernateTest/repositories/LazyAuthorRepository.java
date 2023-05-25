package com.example.hibernateTest.repositories;

import com.example.hibernateTest.entities.LazyAuthor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LazyAuthorRepository extends JpaRepository<LazyAuthor, Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "books")
    List<LazyAuthor> findByAuthorNameContaining(String authorName);

/*    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "books")
    List<LazyAuthor> findAll();*/
}
