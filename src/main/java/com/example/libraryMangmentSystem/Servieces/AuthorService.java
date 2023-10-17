package com.example.libraryMangmentSystem.Servieces;


import com.example.libraryMangmentSystem.Entities.Author;
import com.example.libraryMangmentSystem.Entities.Book;
import com.example.libraryMangmentSystem.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository obj;
    public void addAuthor(Author author) {

        obj.save(author);
    }

    public List<String> findAuthor() {


        List<String> list = new ArrayList<>();
        List<Author> aList = obj.findAll();
        for(Author author:aList){
            list.add(author.getAuthorName());
        }
        return list;
    }

    public Author getAuthorById(Integer id) throws Exception {

        Optional<Author> authorOptional = obj.findById(id);

        if(!authorOptional.isPresent()){
            throw new Exception("This id is invalid");
        }
        Author author = authorOptional.get();
        return author;
    }

    public List<String> getList(Integer authorId) {

        List<String> BookName = new ArrayList<>();

        Author author = obj.findById(authorId).get();
        List<Book> bookList = author.getBookList();

        for(Book book:bookList){
            BookName.add(book.getBookName());
        }
        return BookName;
    }
}
