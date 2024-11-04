package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.Credit_CardModel;
import com.edu.unbosque.Digital.FinServ.Repository.Credit_CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class Credit_CardService {

    @Autowired
    private Credit_CardRepository creditCardRepository;

    /**
     * Crea una nueva tarjeta de crédito con un límite de crédito aleatorio.
     *
     * @param creditCard el modelo de la tarjeta de crédito a crear
     * @return la tarjeta de crédito creada
     */
    @Transactional
    public Credit_CardModel createCreditCard(Credit_CardModel creditCard) {
        // Generar un límite de crédito aleatorio entre 1000 y 10000
        double randomCreditLimit = 1000 + new Random().nextInt(9000);
        creditCard.setCreditLimit(randomCreditLimit);
        creditCard.setAvailableBalance(randomCreditLimit); // El balance es igual al límite de crédito
        creditCard.setExpirationDate(new Date()); // Ajusta la fecha de expiración según sea necesario

        return creditCardRepository.save(creditCard);
    }

    /**
     * Obtiene todas las tarjetas de crédito de un cliente específico.
     *
     * @param customerId el ID del cliente
     * @return una lista de tarjetas de crédito asociadas al cliente
     */
    public List<Credit_CardModel> getCreditCardsByCustomerId(int customerId) {
        return creditCardRepository.findByCustomerId(customerId);
    }

    /**
     * Obtiene una tarjeta de crédito por su ID.
     *
     * @param id el ID de la tarjeta de crédito
     * @return la tarjeta de crédito, si existe
     */
    public Optional<Credit_CardModel> getCreditCardById(int id) {
        return creditCardRepository.findById(id);
    }

    /**
     * Actualiza el límite de crédito de una tarjeta existente y ajusta el balance disponible.
     *
     * @param cardId el ID de la tarjeta de crédito
     * @param newCreditLimit el nuevo límite de crédito
     * @return la tarjeta de crédito actualizada
     */
    @Transactional
    public Credit_CardModel updateCreditCard(int cardId, double newCreditLimit) {
        Credit_CardModel existingCard = creditCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Credit card not found with id " + cardId));

        existingCard.setCreditLimit(newCreditLimit);
        existingCard.setAvailableBalance(newCreditLimit); // Actualizar el balance al nuevo límite de crédito
        return creditCardRepository.save(existingCard);
    }

    /**
     * Elimina una tarjeta de crédito por su ID.
     *
     * @param cardId el ID de la tarjeta de crédito a eliminar
     */
    @Transactional
    public void deleteCreditCard(int cardId) {
        Credit_CardModel existingCard = creditCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Credit card not found with id " + cardId));

        creditCardRepository.delete(existingCard);
    }
}
