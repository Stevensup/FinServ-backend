package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.Credit_CardModel;
import com.edu.unbosque.Digital.FinServ.Repository.Credit_CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing credit cards.
 */
@Service
public class Credit_CardService {

    @Autowired
    private Credit_CardRepository credit_cardRepository;

    /**
     * Creates a new credit card.
     *
     * @param credit_card the credit card to create
     * @return the created credit card
     */
    public Credit_CardModel createCredit_Card(Credit_CardModel credit_card) {
        return credit_cardRepository.save(credit_card);
    }

    /**
     * Retrieves a credit card by its ID.
     *
     * @param id the ID of the credit card
     * @return an Optional containing the credit card if found, or empty if not found
     */
    public Optional<Credit_CardModel> getCreditCardById(int id) {
        return credit_cardRepository.findById(id);
    }

    /**
     * Retrieves all credit cards.
     *
     * @return a list of all credit cards
     */
    public List<Credit_CardModel> getAllCreditCards() {
        return credit_cardRepository.findAll();
    }

    /**
     * Updates an existing credit card.
     *
     * @param id the ID of the credit card to update
     * @param credit_card the updated credit card data
     * @return the updated credit card
     * @throws RuntimeException if the credit card with the specified ID is not found
     */
    public Credit_CardModel updateCreditCard(int id, Credit_CardModel credit_card) {
        if(credit_cardRepository.existsById(id)) {
            credit_card.setIdCreditCard(id);
            return credit_cardRepository.save(credit_card);
        } else {
            throw new RuntimeException("Credit Card not found with id " + id);
        }
    }

    /**
     * Deletes a credit card by its ID.
     *
     * @param id the ID of the credit card to delete
     * @throws RuntimeException if the credit card with the specified ID is not found
     */
    public void deleteCreditCard(int id) {
        if(credit_cardRepository.existsById(id)) {
            credit_cardRepository.deleteById(id);
        } else {
            throw new RuntimeException("Credit Card not found with id " + id);
        }
    }
}