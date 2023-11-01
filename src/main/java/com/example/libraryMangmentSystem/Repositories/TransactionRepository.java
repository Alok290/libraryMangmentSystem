package com.example.libraryMangmentSystem.Repositories;

import com.example.libraryMangmentSystem.Entities.Book;
import com.example.libraryMangmentSystem.Entities.LibraryCard;
import com.example.libraryMangmentSystem.Entities.Transaction;
import com.example.libraryMangmentSystem.Enum.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Transaction findTransactionByBookAndCardAndTransactionStatus(Book book, LibraryCard card, TransactionStatus transactionStatus);
}
