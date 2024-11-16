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

    /**
     * Create a new transaction.
     *
     * @param transaction data needed to create a new transaction
     * @return the created transaction
     */
    @PostMapping("/create")
    @Operation(summary = "Create a new transaction", description = "Create a new transaction")
    @ApiResponse(
            responseCode = "200", description = "Transaction created"
    )
    public TransactionsModel createTransaction(@RequestBody TransactionsModel transaction) {
        return transactionsService.createTransaction(transaction);
    }

    /**
     * Get a transaction by ID.
     *
     * @param id the ID of the transaction
     * @return the transaction found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a transaction by ID", description = "Get a transaction by ID")
    @ApiResponse(
            responseCode = "200", description = "Transaction found"
    )
    public Optional<TransactionsModel> getTransactionById(@PathVariable int id) {
        return transactionsService.getTransactionById(id);
    }

    /**
     * Get all transactions.
     *
     * @return all transactions
     */
    @GetMapping("/all")
    @Operation(summary = "Get all transactions", description = "Get all transactions")
    @ApiResponse(
            responseCode = "200", description = "Transactions retrieved"
    )
    public List<TransactionsModel> getAllTransactions() {
        return transactionsService.getAllTransactions();
    }

    /**
     * Update a transaction.
     *
     * @param id the ID of the transaction
     * @param transaction data needed to update the transaction
     * @return the updated transaction
     */
    @PutMapping("/update/{id}")
    @Operation(summary = "Update a transaction", description = "Update a transaction")
    @ApiResponse(
            responseCode = "200", description = "Transaction updated"
    )
    public TransactionsModel updateTransaction(@PathVariable int id, @RequestBody TransactionsModel transaction) {
        return transactionsService.updateTransaction(id, transaction);
    }

    /**
     * Delete a transaction.
     *
     * @param id the ID of the transaction
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a transaction", description = "Delete a transaction")
    @ApiResponse(
            responseCode = "200", description = "Transaction deleted"
    )
    public void deleteTransaction(@PathVariable int id) {
        transactionsService.deleteTransaction(id);
    }
}