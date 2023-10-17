package com.example.libraryMangmentSystem.Controllers;

import com.example.libraryMangmentSystem.Entities.Author;
import com.example.libraryMangmentSystem.Servieces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    AuthorService obj;
    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody Author author){

        obj.addAuthor(author);
        return new ResponseEntity<>("Author Added Successfully", HttpStatus.OK);
    }


    @GetMapping("/getAuthor")
    public ResponseEntity<List<String>> getAuthor(){
       List<String> list=obj.findAuthor();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/getAuthorById/{id}")
    public ResponseEntity getAuthBy(@PathVariable("id") Integer id) throws Exception{

        try {
            Author author = obj.getAuthorById(id);
            return  new ResponseEntity<>(author,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/getBookList")
    public  ResponseEntity getList(@RequestParam ("authorId") Integer authorId){
        List<String> list = obj.getList(authorId);
        return new ResponseEntity (list,HttpStatus.OK);
    }
}
