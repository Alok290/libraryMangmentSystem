package com.example.libraryMangmentSystem.Controllers;

import com.example.libraryMangmentSystem.Entities.Book;
import com.example.libraryMangmentSystem.Servieces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.rsocket.server.RSocketServerException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody Book book, @RequestParam("authorId") Integer authorId){

        try{
            String ans = bookService.addBook(book,authorId);
            return new ResponseEntity(ans, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

}
