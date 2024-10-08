package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.Transaction_TypesModel;
import com.edu.unbosque.Digital.FinServ.Repository.Transaction_TypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Transaction_TypesService {

    @Autowired
    private Transaction_TypesRepository transactionTypesRepository;

    public Transaction_TypesModel createTransactionType(Transaction_TypesModel transactionType) {
        return transactionTypesRepository.save(transactionType);
    }

    public Optional<Transaction_TypesModel> getTransactionTypeById(int id) {
        return transactionTypesRepository.findById(id);
    }

    public List<Transaction_TypesModel> getAllTransactionTypes() {
        return transactionTypesRepository.findAll();
    }

    public Transaction_TypesModel updateTransactionType(int id, Transaction_TypesModel transactionType) {
        if (transactionTypesRepository.existsById(id)) {
            transactionType.setTransactionTypeId(id);
            return transactionTypesRepository.save(transactionType);
        } else {
            throw new RuntimeException("Transaction type not found with id " + id);
        }
    }

    public void deleteTransactionType(int id) {
        if (transactionTypesRepository.existsById(id)) {
            transactionTypesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Transaction type not found with id " + id);
        }
    }
}
