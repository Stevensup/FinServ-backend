package com.edu.unbosque.Digital.FinServ.Controller;

import com.edu.unbosque.Digital.FinServ.Model.Credit_CardModel;
import com.edu.unbosque.Digital.FinServ.Service.Credit_CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/creditCards")
public class Credit_CardController {

    @Autowired
    private Credit_CardService creditCardService;

    /**
     * Crea una nueva tarjeta de crédito.
     *
     * @param payload datos necesarios para la creación de la tarjeta de crédito
     * @return la tarjeta de crédito creada
     */
    @PostMapping("/create")
    public ResponseEntity<?> createCreditCard(@RequestBody Map<String, Object> payload) {
        try {
            int customerId = (int) payload.get("customerId");
            double creditLimit = (double) payload.get("creditLimit");
            double availableBalance = (double) payload.get("availableBalance");
            String expirationDate = (String) payload.get("expirationDate");
            String productTypeName = (String) payload.get("productTypeName");

            Credit_CardModel creditCard = new Credit_CardModel();
            creditCard.setCustomerId(customerId);
            creditCard.setCreditLimit(creditLimit);
            creditCard.setAvailableBalance(availableBalance);
            creditCard.setExpirationDate(new SimpleDateFormat("yyyy-MM-dd").parse(expirationDate));

            Credit_CardModel createdCard = creditCardService.createCreditCard(creditCard, productTypeName);
            return ResponseEntity.ok(createdCard);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating credit card: " + e.getMessage());
        }
    }
}
