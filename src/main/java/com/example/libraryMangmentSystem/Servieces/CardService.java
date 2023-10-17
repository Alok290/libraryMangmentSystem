package com.example.libraryMangmentSystem.Servieces;


import com.example.libraryMangmentSystem.Entities.LibraryCard;
import com.example.libraryMangmentSystem.Entities.Student;
import com.example.libraryMangmentSystem.Enum.CardStatus;
import com.example.libraryMangmentSystem.Repositories.CardRepository;
import com.example.libraryMangmentSystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;


    public LibraryCard generateCard() {

        LibraryCard libraryCard = new LibraryCard();

        libraryCard.setCardStatus(CardStatus.NEW);

        return cardRepository.save(libraryCard);
    }

    public String associateWithStudent(Integer studentId, Integer cardNo) {

        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Student student = optionalStudent.get();

        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardNo);
        LibraryCard card = optionalLibraryCard.get();

        card.setCardStatus(CardStatus.ACTIVE);
        card.setNameOnCard(student.getName());
        card.setStudent(student);

        student.setLibraryCard(card);

        studentRepository.save(student);

        return "Card with "+cardNo+" has been associated to student with "+studentId;

    }
}
