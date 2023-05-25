package com.example.hibernateTest.repositories;

import com.example.hibernateTest.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
