package com.example.libraryMangmentSystem.Controllers;

import com.example.libraryMangmentSystem.Entities.LibraryCard;
import com.example.libraryMangmentSystem.Servieces.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/generatePlainCard")
    public ResponseEntity generatePlainCard(){

        LibraryCard newCard = cardService.generateCard();
        String response = "This is new card"+newCard.getCardNo();
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping("/associateWithStudent")
    public String associateWithStudent(@RequestParam ("studentId") Integer studentId,@RequestParam("cardNo") Integer cardNo){

        String result = cardService.associateWithStudent(studentId,cardNo);
        return result;
    }

}
