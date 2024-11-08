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

@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8082", "*" })
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
            // Convertir los datos para evitar problemas de tipo
            int customerId = Integer.parseInt(payload.get("customerId").toString());
            double creditLimit = Double.parseDouble(payload.get("creditLimit").toString());
            double availableBalance = Double.parseDouble(payload.get("availableBalance").toString());
            String expirationDate = payload.get("expirationDate").toString();
            String productTypeName = payload.get("productTypeName").toString();

            // Crear el modelo de tarjeta de crédito
            Credit_CardModel creditCard = new Credit_CardModel();
            creditCard.setCustomerId(customerId);
            creditCard.setCreditLimit(creditLimit);
            creditCard.setAvailableBalance(availableBalance);
            creditCard.setExpirationDate(new SimpleDateFormat("yyyy-MM-dd").parse(expirationDate));

            // Llamar al servicio para crear la tarjeta de crédito
            Credit_CardModel createdCard = creditCardService.createCreditCard(creditCard, productTypeName);
            return ResponseEntity.ok(createdCard);

        } catch (Exception e) {
            e.printStackTrace(); // Ver el error en la consola
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creando la tarjeta de crédito: " + e.getMessage());
        }
    }

    @PutMapping("/updateLimit/{id}")
    public ResponseEntity<?> updateCreditLimit(@PathVariable int id, @RequestBody Map<String, Object> payload) {
        try {
            double newCreditLimit = Double.parseDouble(payload.get("creditLimit").toString());
            Credit_CardModel updatedCard = creditCardService.updateCreditLimit(id, newCreditLimit);
            return ResponseEntity.ok(updatedCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error actualizando el límite de crédito: " + e.getMessage());
        }
    }

    @GetMapping("/detailsByCustomer/{customerId}")
    public ResponseEntity<?> getCreditCardsByCustomerId(@PathVariable int customerId) {
        try {
            List<Credit_CardModel> creditCards = creditCardService.getCreditCardsByCustomerId(customerId);
            return ResponseEntity.ok(creditCards);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error obteniendo tarjetas de crédito: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCreditCard(@PathVariable int id) {
        try {
            creditCardService.deleteCreditCard(id);
            return ResponseEntity.ok("Tarjeta de crédito eliminada con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error eliminando la tarjeta de crédito: " + e.getMessage());
        }
    }

}
