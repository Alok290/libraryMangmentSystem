package com.example.libraryMangmentSystem.Servieces;

import com.example.libraryMangmentSystem.Entities.Author;
import com.example.libraryMangmentSystem.Entities.Book;
import com.example.libraryMangmentSystem.Exception.authorNotFoundException;
import com.example.libraryMangmentSystem.Repositories.AuthorRepository;
import com.example.libraryMangmentSystem.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;
    public String addBook(Book book, Integer authorId) throws Exception {

        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if(!optionalAuthor.isPresent()){
            throw new authorNotFoundException("this id is not valid");
        }
        Author author = optionalAuthor.get();

        book.setAuthor(author);
        author.getBookList().add(book);

        authorRepository.save(author);
        return "Book succesfully entered";
    }
}
