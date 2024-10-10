package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.TransactionsModel;
import com.edu.unbosque.Digital.FinServ.Repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    // Método para crear una nueva transacción
    public TransactionsModel createTransaction(TransactionsModel transaction) {
        return transactionsRepository.save(transaction);
    }

    // Método para obtener una transacción por su ID
    public Optional<TransactionsModel> getTransactionById(int id) {
        return transactionsRepository.findById(id);
    }

    // Método para obtener todas las transacciones
    public List<TransactionsModel> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    // Método para actualizar una transacción existente
    public TransactionsModel updateTransaction(int id, TransactionsModel transaction) {
        if (transactionsRepository.existsById(id)) {
            transaction.setTransactionId(id);
            return transactionsRepository.save(transaction);
        } else {
            throw new RuntimeException("Transaction not found with id " + id);
        }
    }

    // Método para eliminar una transacción por su ID
    public void deleteTransaction(int id) {
        if (transactionsRepository.existsById(id)) {
            transactionsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Transaction not found with id " + id);
        }
    }
}
