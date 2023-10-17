package com.example.libraryMangmentSystem.Repositories;

import com.example.libraryMangmentSystem.Entities.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard,Integer> {
}
