package com.example.libraryMangmentSystem.Servieces;


import com.example.libraryMangmentSystem.Entities.Student;
import com.example.libraryMangmentSystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    public void addStudent(Student student) {
        studentRepository.save(student);
    }
}
