package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.Transaction_LogsModel;
import com.edu.unbosque.Digital.FinServ.Repository.Transaction_LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class Transaction_LogsService {

    @Autowired
    private Transaction_LogsRepository transactionLogsRepository;

    // Método para crear un nuevo registro de log
    public Transaction_LogsModel createLog(Transaction_LogsModel transactionLog) {
        return transactionLogsRepository.save(transactionLog);
    }

    // Método para obtener un registro de log por ID
    public Optional<Transaction_LogsModel> getLogById(int id) {
        return transactionLogsRepository.findById(id);
    }

    // Método para obtener todos los registros de log
    public List<Transaction_LogsModel> getAllLogs() {
        return transactionLogsRepository.findAll();
    }

    // Método para actualizar un registro de log
    public Transaction_LogsModel updateLog(int id, Transaction_LogsModel transactionLog) {
        if (transactionLogsRepository.existsById(id)) {
            transactionLog.setLogId(id);
            return transactionLogsRepository.save(transactionLog);
        } else {
            throw new RuntimeException("Log not found with id " + id);
        }
    }

    // Método para eliminar un registro de log
    public void deleteLog(int id) {
        if (transactionLogsRepository.existsById(id)) {
            transactionLogsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Log not found with id " + id);
        }
    }
}
