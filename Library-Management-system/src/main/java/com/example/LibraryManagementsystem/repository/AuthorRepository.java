package com.example.LibraryManagementsystem.repository;

import com.example.LibraryManagementsystem.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author findByEmail(String email);
}
