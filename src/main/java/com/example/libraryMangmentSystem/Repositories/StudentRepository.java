package com.example.libraryMangmentSystem.Repositories;

import com.example.libraryMangmentSystem.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
