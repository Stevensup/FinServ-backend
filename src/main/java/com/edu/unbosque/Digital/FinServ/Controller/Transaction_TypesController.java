package com.edu.unbosque.Digital.FinServ.Controller;

import com.edu.unbosque.Digital.FinServ.Model.Transaction_TypesModel;
import com.edu.unbosque.Digital.FinServ.Service.Transaction_TypesService;
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
@RequestMapping("/transaction-types")
public class Transaction_TypesController {

    @Autowired
    private Transaction_TypesService transactionTypesService;

    @PostMapping("/create")
    @Operation(
            summary = "Create a new transaction type",
            description = "Create a new transaction type"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Transaction type created"
    )
    public Transaction_TypesModel createTransactionType(@RequestBody Transaction_TypesModel transactionType) {
        return transactionTypesService.createTransactionType(transactionType);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a transaction type by ID", description = "Get a transaction type by ID")
    @ApiResponse(responseCode = "200", description = "Transaction type found")
    public Optional<Transaction_TypesModel> getTransactionTypeById(@PathVariable int id) {
        return transactionTypesService.getTransactionTypeById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all transaction types", description = "Get all transaction types")
    @ApiResponse(responseCode = "200", description = "Transaction types retrieved")
    public List<Transaction_TypesModel> getAllTransactionTypes() {
        return transactionTypesService.getAllTransactionTypes();
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a transaction type", description = "Update a transaction type")
    @ApiResponse(responseCode = "200", description = "Transaction type updated")
    public Transaction_TypesModel updateTransactionType(@PathVariable int id, @RequestBody Transaction_TypesModel transactionType) {
        return transactionTypesService.updateTransactionType(id, transactionType);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a transaction type", description = "Delete a transaction type")
    @ApiResponse(responseCode = "200", description = "Transaction type deleted")
    public void deleteTransactionType(@PathVariable int id) {
        transactionTypesService.deleteTransactionType(id);
    }
}