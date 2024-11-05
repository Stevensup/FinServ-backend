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

    /**
     * Crea una nueva tarjeta de crédito.
     *
     * @param creditCard el modelo de la tarjeta de crédito a crear
     * @return la tarjeta de crédito creada
     */
    @PostMapping("/create")
    public ResponseEntity<Credit_CardModel> createCreditCard(@RequestBody Credit_CardModel creditCard) {
        Credit_CardModel createdCard = creditCardService.createCreditCard(creditCard);
        return ResponseEntity.ok(createdCard);
    }

    /**
     * Elimina una tarjeta de crédito por su ID.
     *
     * @param id el ID de la tarjeta de crédito a eliminar
     * @return mensaje de confirmación
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCreditCard(@PathVariable int id) {
        creditCardService.deleteCreditCard(id);
        return ResponseEntity.ok("Credit card deleted successfully.");
    }

    /**
     * Obtiene todas las tarjetas de crédito de un cliente específico.
     *
     * @param customerId el ID del cliente
     * @return lista de tarjetas de crédito asociadas al cliente
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Credit_CardModel>> getCreditCardsByCustomerId(@PathVariable int customerId) {
        List<Credit_CardModel> creditCards = creditCardService.getCreditCardsByCustomerId(customerId);
        return ResponseEntity.ok(creditCards);
    }

    /**
     * Actualiza el límite de crédito de una tarjeta específica.
     *
     * @param id el ID de la tarjeta de crédito
     * @param creditLimitUpdate mapa con el nuevo límite de crédito
     * @return la tarjeta de crédito actualizada
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Credit_CardModel> updateCreditCard(@PathVariable int id, @RequestBody Map<String, Double> creditLimitUpdate) {
        Double newCreditLimit = creditLimitUpdate.get("newCreditLimit");
        Credit_CardModel updatedCard = creditCardService.updateCreditCard(id, newCreditLimit);
        return ResponseEntity.ok(updatedCard);
    }
}
