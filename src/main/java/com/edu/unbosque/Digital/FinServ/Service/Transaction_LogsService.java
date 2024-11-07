package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.Transaction_LogsModel;
import com.edu.unbosque.Digital.FinServ.Repository.Transaction_LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing transaction logs.
 */
@Service
public class Transaction_LogsService {

    @Autowired
    private Transaction_LogsRepository transactionLogsRepository;

    /**
     * Creates a new transaction log.
     *
     * @param transactionLog the transaction log to create
     * @return the created transaction log
     */
    public Transaction_LogsModel createTransactionLog(Transaction_LogsModel transactionLog) {
        return transactionLogsRepository.save(transactionLog);
    }

    /**
     * Retrieves a transaction log by its ID.
     *
     * @param id the ID of the transaction log
     * @return an Optional containing the transaction log if found, or empty if not found
     */
    public Optional<Transaction_LogsModel> getTransactionLogById(int id) {
        return transactionLogsRepository.findById(id);
    }

    /**
     * Retrieves all transaction logs.
     *
     * @return a list of all transaction logs
     */
    public List<Transaction_LogsModel> getAllTransactionLogs() {
        return transactionLogsRepository.findAll();
    }

    /**
     * Updates an existing transaction log.
     *
     * @param id the ID of the transaction log to update
     * @param transactionLog the updated transaction log data
     * @return the updated transaction log
     * @throws RuntimeException if the transaction log with the specified ID is not found
     */
    public Transaction_LogsModel updateTransactionLog(int id, Transaction_LogsModel transactionLog) {
        if (transactionLogsRepository.existsById(id)) {
            transactionLog.setLogId(id);
            return transactionLogsRepository.save(transactionLog);
        } else {
            throw new RuntimeException("Log not found with id " + id);
        }
    }

    /**
     * Deletes a transaction log by its ID.
     *
     * @param id the ID of the transaction log to delete
     * @throws RuntimeException if the transaction log with the specified ID is not found
     */
    public void deleteTransactionLog(int id) {
        if (transactionLogsRepository.existsById(id)) {
            transactionLogsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Log not found with id " + id);
        }
    }
}