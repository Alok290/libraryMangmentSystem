package com.example.libraryMangmentSystem.Repositories;

import com.example.libraryMangmentSystem.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
