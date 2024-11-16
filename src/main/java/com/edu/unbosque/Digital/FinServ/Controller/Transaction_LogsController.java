package com.edu.unbosque.Digital.FinServ.Controller;

import com.edu.unbosque.Digital.FinServ.Model.Transaction_LogsModel;
import com.edu.unbosque.Digital.FinServ.Service.Transaction_LogsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/transaction-logs")
public class Transaction_LogsController {

    @Autowired
    private Transaction_LogsService transactionLogsService;

    @PostMapping("/create")
    @Operation(summary = "Create a new transaction log", description = "Create a new transaction log")
    @ApiResponse(
            responseCode = "200", description = "Transaction log created"
            responseCode = "400", description = "Invalid input"
    )
    public Transaction_LogsModel createTransactionLog(@RequestBody Transaction_LogsModel transactionLog) {
        return transactionLogsService.createTransactionLog(transactionLog);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a transaction log by ID", description = "Get a transaction log by ID")
    @ApiResponse(
            responseCode = "200", description = "Transaction log found"
            responseCode = "400", description = "Invalid input"
    )
    public Optional<Transaction_LogsModel> getTransactionLogById(@PathVariable int id) {
        return transactionLogsService.getTransactionLogById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all transaction logs", description = "Get all transaction logs")
    @ApiResponse(
            responseCode = "200", description = "Transaction logs retrieved"
            responseCode = "400", description = "Invalid input"
    )
    public List<Transaction_LogsModel> getAllTransactionLogs() {
        return transactionLogsService.getAllTransactionLogs();
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a transaction log", description = "Update a transaction log")
    @ApiResponse(
            responseCode = "200", description = "Transaction log updated"
            responseCode = "400", description = "Invalid input"
        )
    public Transaction_LogsModel updateTransactionLog(@PathVariable int id, @RequestBody Transaction_LogsModel transactionLog) {
        return transactionLogsService.updateTransactionLog(id, transactionLog);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a transaction log", description = "Delete a transaction log")
    @ApiResponse(
            responseCode = "200", description = "Transaction log deleted"
            responseCode = "400", description = "Invalid input"
    )
    public void deleteTransactionLog(@PathVariable int id) {
        transactionLogsService.deleteTransactionLog(id);
    }
}