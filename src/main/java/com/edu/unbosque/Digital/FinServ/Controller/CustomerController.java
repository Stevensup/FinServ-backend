package com.edu.unbosque.Digital.FinServ.Controller;


import com.edu.unbosque.Digital.FinServ.Model.CustomerModel;
import com.edu.unbosque.Digital.FinServ.Service.CustomerService;
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
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    @Operation(
            summary = "Create a new customer",
            description = "Create a new customer"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Customer created"
    )
    public CustomerModel createCustomer(@RequestBody CustomerModel customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a customer by ID", description = "Get a customer by ID")
    @ApiResponse(responseCode = "200", description = "Customer found")
    public Optional<CustomerModel> getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all customers", description = "Get all customers")
    @ApiResponse(responseCode = "200", description = "Customers retrieved")
    public List<CustomerModel> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a customer", description = "Update a customer")
    @ApiResponse(responseCode = "200", description = "Customer updated")
    public CustomerModel updateCustomer(@PathVariable int id, @RequestBody CustomerModel customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a customer", description = "Delete a customer")
    @ApiResponse(responseCode = "200", description = "Customer deleted")
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }
}
