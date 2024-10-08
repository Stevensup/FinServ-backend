package com.edu.unbosque.Digital.FinServ.Controller;

import com.edu.unbosque.Digital.FinServ.Model.TransactionsModel;
import com.edu.unbosque.Digital.FinServ.Service.TransactionsService;
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
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @PostMapping("/create")
    @Operation(
            summary = "Create a new transaction",
            description = "Create a new transaction"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Transaction created"
    )
    public TransactionsModel createTransaction(@RequestBody TransactionsModel transaction) {
        return transactionsService.createTransaction(transaction);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a transaction by ID", description = "Get a transaction by ID")
    @ApiResponse(responseCode = "200", description = "Transaction found")
    public Optional<TransactionsModel> getTransactionById(@PathVariable int id) {
        return transactionsService.getTransactionById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all transactions", description = "Get all transactions")
    @ApiResponse(responseCode = "200", description = "Transactions retrieved")
    public List<TransactionsModel> getAllTransactions() {
        return transactionsService.getAllTransactions();
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a transaction", description = "Update a transaction")
    @ApiResponse(responseCode = "200", description = "Transaction updated")
    public TransactionsModel updateTransaction(@PathVariable int id, @RequestBody TransactionsModel transaction) {
        return transactionsService.updateTransaction(id, transaction);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a transaction", description = "Delete a transaction")
    @ApiResponse(responseCode = "200", description = "Transaction deleted")
    public void deleteTransaction(@PathVariable int id) {
        transactionsService.deleteTransaction(id);
    }
}