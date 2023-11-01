package com.example.libraryMangmentSystem.Servieces;


import com.example.libraryMangmentSystem.Entities.Book;
import com.example.libraryMangmentSystem.Entities.LibraryCard;
import com.example.libraryMangmentSystem.Entities.Transaction;
import com.example.libraryMangmentSystem.Enum.CardStatus;
import com.example.libraryMangmentSystem.Enum.TransactionStatus;
import com.example.libraryMangmentSystem.Exception.BookNotAvalibleException;
import com.example.libraryMangmentSystem.Exception.CardNotFound;
import com.example.libraryMangmentSystem.Exception.InvalidCardStatusException;
import com.example.libraryMangmentSystem.Exception.MaxBookAlreadyIssued;
import com.example.libraryMangmentSystem.Repositories.BookRepository;
import com.example.libraryMangmentSystem.Repositories.CardRepository;
import com.example.libraryMangmentSystem.Repositories.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    private static final Integer MAX_LIMIT_OF_BOOKS=3;

    private static final Integer FINE_PER_DAY = 5;

    public String issueBook(Integer bookId, Integer cardId) throws Exception {

        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(TransactionStatus.PENDING);


        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if(!bookOptional.isPresent()){
            throw new BookNotAvalibleException("Book is not Available");
        }
        Book book = bookOptional.get();

        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);

        if(!optionalLibraryCard.isPresent()){
            throw new CardNotFound("Entered cardId is Invalid");
        }

        LibraryCard libraryCard = optionalLibraryCard.get();

        if(!libraryCard.getCardStatus().equals(CardStatus.ACTIVE)){

            throw new InvalidCardStatusException("Card status is not Active");

        }

        if(libraryCard.getNoOfBooksIssued()==MAX_LIMIT_OF_BOOKS){
            throw new MaxBookAlreadyIssued(MAX_LIMIT_OF_BOOKS+"is maximum book that can be issued");
        }

        transaction.setTransactionStatus(TransactionStatus.ISSUED);
        libraryCard.setNoOfBooksIssued(libraryCard.getNoOfBooksIssued()+1);

        book.setAvailable(false);


        transaction.setBook(book);
        transaction.setCard(libraryCard);


        book.getTransactionList().add(transaction);
        libraryCard.getTransactionList().add(transaction);

        transactionRepository.save(transaction);

        return "The book with bookId "+bookId+" has been issued " +
                "to card with "+cardId;


    }


    public String returnBook(Integer bookId,Integer cardId){

        Book book = bookRepository.findById(bookId).get();
        LibraryCard card = cardRepository.findById(cardId).get();

        //I need to find out that issue Transaction

        Transaction transaction = (Transaction) transactionRepository.findTransactionByBookAndCardAndTransactionStatus(book,card,TransactionStatus.ISSUED);

        Date issueDate = transaction.getCreatedOn();

        //Predefined method that you can use to calculate days
        long milliSeconds = Math.abs(System.currentTimeMillis()-issueDate.getTime());
        Long days = TimeUnit.DAYS.convert(milliSeconds,TimeUnit.MILLISECONDS);

        int fineAmount = 0;

        if(days>15){
            fineAmount = Math.toIntExact((days - 15) * FINE_PER_DAY);
        }

        Transaction newTransaction = new Transaction();

        newTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
        newTransaction.setReturnDate(new Date());
        newTransaction.setFine(fineAmount);

        //Setting the FK's
        newTransaction.setBook(book);
        newTransaction.setCard(card);

        book.setAvailable(true);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()-1);

        book.getTransactionList().add(newTransaction);
        card.getTransactionList().add(newTransaction);

        transactionRepository.save(newTransaction);

        return "book has been returned";

    }


}