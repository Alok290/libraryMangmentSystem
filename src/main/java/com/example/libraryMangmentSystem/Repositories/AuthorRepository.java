package com.example.libraryMangmentSystem.Repositories;

import com.example.libraryMangmentSystem.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
}

