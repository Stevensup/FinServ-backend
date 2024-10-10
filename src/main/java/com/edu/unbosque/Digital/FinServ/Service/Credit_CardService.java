package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.Credit_CardModel;
import com.edu.unbosque.Digital.FinServ.Repository.Credit_CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class Credit_CardService {

    @Autowired
    private Credit_CardRepository credit_cardRepository;

    public Credit_CardModel createCredit_Card(Credit_CardModel credit_card) {
        return credit_cardRepository.save(credit_card);        
    }     

    public Optional<Credit_CardModel> getCreditCardById(int id) {
        return credit_cardRepository.findById(id);
    }

    public List<Credit_CardModel> getAllCreditCards() {
        return credit_cardRepository.findAll();
    }

    public Credit_CardModel updateCreditCard(int id, Credit_CardModel credit_card) {
        if(credit_cardRepository.existsById(id)) {
            credit_card.setIdCreditCard(id);
            return credit_cardRepository.save(credit_card);
        } else {
            throw new RuntimeException("Credit Card not found with id " + id);
        }       
    }

    public void deleteCreditCard(int id) {
        if(credit_cardRepository.existsById(id)) {
            credit_cardRepository.deleteById(id);
        } else {    
            throw new RuntimeException("Credit Card not found with id " + id);
        }
    }
}
