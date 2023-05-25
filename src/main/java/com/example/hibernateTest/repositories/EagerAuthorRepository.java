package com.example.hibernateTest.repositories;

import com.example.hibernateTest.entities.EagerAuthor;
import com.example.hibernateTest.entities.LazyAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EagerAuthorRepository extends JpaRepository<EagerAuthor, Long> {
    List<EagerAuthor> findByAuthorNameContaining(String authorName);
}
