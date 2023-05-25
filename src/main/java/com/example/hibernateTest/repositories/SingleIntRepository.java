package com.example.hibernateTest.repositories;

import com.example.hibernateTest.entities.SingleInt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingleIntRepository extends JpaRepository<SingleInt, Long> {
}
