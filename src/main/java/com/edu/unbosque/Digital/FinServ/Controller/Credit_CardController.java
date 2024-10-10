package com.edu.unbosque.Digital.FinServ.Controller;

import com.edu.unbosque.Digital.FinServ.Model.Credit_CardModel;
import com.edu.unbosque.Digital.FinServ.Service.Credit_CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Transactional
@CrossOrigin(
    origins = {"http://localhost:8090","http://localhost:8080","*"}
    )
@RestController
@RequestMapping("/credit_card")

public class Credit_CardController {

    @Autowired
    private Credit_CardService credit_cardService;
    
    @PostMapping("/create")
    @Operation(
        summary = "Create a new credit card",
        description = "Create a new credit card"
    )

    @ApiResponse(
        responseCode = "201",
        description = "Credit card created successfully"
    )

    public Credit_CardModel createCreditCard(@RequestBody Credit_CardModel credit_card) {
        return credit_cardService.createCredit_Card(credit_card);
    }

    @GetMapping({"/{id}"})
    @Operation(
        summary = "Get credit card by id",
        description = "Get credit card by id"
    )

    @ApiResponse(
        responseCode = "200",
        description = "Credit card found"
    )

    public Optional<Credit_CardModel> getCreditCardById(@PathVariable int id) {
        return credit_cardService.getCreditCardById(id);
    }

    @GetMapping({"/all"})
    @Operation(
        summary = "Get all credit cards",
        description = "Get all credit cards"
    )

    @ApiResponse(
        responseCode = "200",
        description = "Credit cards retrieved"
    )

    public List<Credit_CardModel> getAllCreditCards() {
        return credit_cardService.getAllCreditCards();
    }

    @PutMapping({"/update/{id}"})
    @Operation(
        summary = "Update credit card by id",
        description = "Update credit card by id"
    )

    @ApiResponse(
        responseCode = "200",
        description = "Credit card updated"
    )

    public Credit_CardModel updateCreditCard(@PathVariable int id, @RequestBody Credit_CardModel credit_card) { 
        return credit_cardService.updateCreditCard(id, credit_card);
    }

    @DeleteMapping({"/delete/{id}"})
    @Operation(
        summary = "Delete credit card by id",
        description = "Delete credit card by id"
    )

    @ApiResponse(
        responseCode = "200",
        description = "Credit card deleted"
    )

    public void deleteCreditCard(@PathVariable int id) {
        credit_cardService.deleteCreditCard(id);
    }
}
