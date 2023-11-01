package com.example.libraryMangmentSystem.Entities;


import com.example.libraryMangmentSystem.Enum.CardStatus;
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
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardNo;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private String nameOnCard;

    private Integer noOfBooksIssued;


    @OneToOne
    @JoinColumn
    private Student student;


    @OneToMany (mappedBy = "card",cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();
}
