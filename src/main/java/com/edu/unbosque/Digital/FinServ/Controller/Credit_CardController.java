package com.edu.unbosque.Digital.FinServ.Controller;

import com.edu.unbosque.Digital.FinServ.Model.Credit_CardModel;
import com.edu.unbosque.Digital.FinServ.Service.Credit_CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/creditCards")
public class Credit_CardController {

    @Autowired
    private Credit_CardService creditCardService;

    @PostMapping("/create")
    public ResponseEntity<Credit_CardModel> createCreditCard(@RequestBody Credit_CardModel creditCard) {
        return ResponseEntity.ok(creditCardService.createCreditCard(creditCard));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCreditCard(@PathVariable int id) {
        creditCardService.deleteCreditCard(id);
        return ResponseEntity.ok("Credit card deleted successfully.");
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Credit_CardModel>> getCreditCardsByCustomerId(@PathVariable int customerId) {
        return ResponseEntity.ok(creditCardService.getCreditCardsByCustomerId(customerId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Credit_CardModel> updateCreditCard(@PathVariable int id, @RequestBody Map<String, Double> creditLimitUpdate) {
        Double newCreditLimit = creditLimitUpdate.get("newCreditLimit");
        return ResponseEntity.ok(creditCardService.updateCreditCard(id, newCreditLimit));
    }
}
