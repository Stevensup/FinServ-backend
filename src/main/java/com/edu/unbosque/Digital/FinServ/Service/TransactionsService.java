package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.TransactionsModel;
import com.edu.unbosque.Digital.FinServ.Repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing transactions.
 */
@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    /**
     * Creates a new transaction.
     *
     * @param transaction the transaction to create
     * @return the created transaction
     */
    public TransactionsModel createTransaction(TransactionsModel transaction) {
        return transactionsRepository.save(transaction);
    }

    /**
     * Retrieves a transaction by its ID.
     *
     * @param id the ID of the transaction
     * @return an Optional containing the transaction if found, or empty if not found
     */
    public Optional<TransactionsModel> getTransactionById(int id) {
        return transactionsRepository.findById(id);
    }

    /**
     * Retrieves all transactions.
     *
     * @return a list of all transactions
     */
    public List<TransactionsModel> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    /**
     * Updates an existing transaction.
     *
     * @param id the ID of the transaction to update
     * @param transaction the updated transaction data
     * @return the updated transaction
     * @throws RuntimeException if the transaction with the specified ID is not found
     */
    public TransactionsModel updateTransaction(int id, TransactionsModel transaction) {
        if (transactionsRepository.existsById(id)) {
            transaction.setTransactionId(id);
            return transactionsRepository.save(transaction);
        } else {
            throw new RuntimeException("Transaction not found with id " + id);
        }
    }

    /**
     * Deletes a transaction by its ID.
     *
     * @param id the ID of the transaction to delete
     * @throws RuntimeException if the transaction with the specified ID is not found
     */
    public void deleteTransaction(int id) {
        if (transactionsRepository.existsById(id)) {
            transactionsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Transaction not found with id " + id);
        }
    }
}