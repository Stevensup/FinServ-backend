package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.Transaction_TypesModel;
import com.edu.unbosque.Digital.FinServ.Repository.Transaction_TypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing transaction types.
 */
@Service
public class Transaction_TypesService {

    @Autowired
    private Transaction_TypesRepository transactionTypesRepository;

    /**
     * Creates a new transaction type.
     *
     * @param transactionType the transaction type to create
     * @return the created transaction type
     */
    public Transaction_TypesModel createTransactionType(Transaction_TypesModel transactionType) {
        return transactionTypesRepository.save(transactionType);
    }

    /**
     * Retrieves a transaction type by its ID.
     *
     * @param id the ID of the transaction type
     * @return an Optional containing the transaction type if found, or empty if not found
     */
    public Optional<Transaction_TypesModel> getTransactionTypeById(int id) {
        return transactionTypesRepository.findById(id);
    }

    /**
     * Retrieves all transaction types.
     *
     * @return a list of all transaction types
     */
    public List<Transaction_TypesModel> getAllTransactionTypes() {
        return transactionTypesRepository.findAll();
    }

    /**
     * Updates an existing transaction type.
     *
     * @param id the ID of the transaction type to update
     * @param transactionType the updated transaction type data
     * @return the updated transaction type
     * @throws RuntimeException if the transaction type with the specified ID is not found
     */
    public Transaction_TypesModel updateTransactionType(int id, Transaction_TypesModel transactionType) {
        if (transactionTypesRepository.existsById(id)) {
            transactionType.setTransactionTypeId(id);
            return transactionTypesRepository.save(transactionType);
        } else {
            throw new RuntimeException("Transaction type not found with id " + id);
        }
    }

    /**
     * Deletes a transaction type by its ID.
     *
     * @param id the ID of the transaction type to delete
     * @throws RuntimeException if the transaction type with the specified ID is not found
     */
    public void deleteTransactionType(int id) {
        if (transactionTypesRepository.existsById(id)) {
            transactionTypesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Transaction type not found with id " + id);
        }
    }
}