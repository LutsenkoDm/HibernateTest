package com.example.hibernateTest.repositories;

import com.example.hibernateTest.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
