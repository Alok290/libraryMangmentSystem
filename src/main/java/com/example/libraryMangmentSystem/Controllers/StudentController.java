package com.example.libraryMangmentSystem.Controllers;

import com.example.libraryMangmentSystem.Entities.Student;
import com.example.libraryMangmentSystem.Servieces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping("/addStudent")
    public ResponseEntity addStudent(@RequestBody Student student){

        studentService.addStudent(student);
        return new ResponseEntity("succses", HttpStatus.OK);
    }
}
