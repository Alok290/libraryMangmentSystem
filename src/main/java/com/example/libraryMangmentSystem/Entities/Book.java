package com.example.libraryMangmentSystem.Entities;


import com.example.libraryMangmentSystem.Enum.Geners;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    private String bookName; //PostMan

    private int price; //Postman

    private int noOfPages; //Postman

    @Enumerated(value = EnumType.STRING)
    private Geners genre; //Postman

    private double rating; //Postman

    private boolean isAvailable;

    @ManyToOne
    @JoinColumn
    private Author author;


    @OneToMany (mappedBy = "book",cascade= CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();


}
