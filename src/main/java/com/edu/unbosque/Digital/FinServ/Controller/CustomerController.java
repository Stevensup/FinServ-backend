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

    /**
     * Create a new customer.
     *
     * @param customer data needed to create a new customer
     * @return the created customer
     */

    @PostMapping("/create")
    @Operation(summary = "Create a new customer", description = "Create a new customer")
    @ApiResponse(
            responseCode = "200", description = "Customer created"
            responseCode = "500", description = "Error creating customer"
    )
    public CustomerModel createCustomer(@RequestBody CustomerModel customer) {
        return customerService.createCustomer(customer);
    }

    /**
     * Get a customer by ID.
     *
     * @param id the ID of the customer
     * @return the customer found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a customer by ID", description = "Get a customer by ID")
    @ApiResponse(
            responseCode = "200", description = "Customer found"
            responseCode = "404", description = "Customer not found"
    )
    public Optional<CustomerModel> getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    /**
     * Get all customers.
     *
     * @return all customers
     */
    @GetMapping("/all")
    @Operation(summary = "Get all customers", description = "Get all customers")
    @ApiResponse(
            responseCode = "200", description = "Customers retrieved"
            responseCode = "404", description = "Customers not found"
    )
    public List<CustomerModel> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    /**
     * Update a customer.
     *
     * @param id the ID of the customer
     * @param customer data needed to update the customer
     * @return the updated customer
     */
    @PutMapping("/update/{id}")
    @Operation(summary = "Update a customer", description = "Update a customer")
    @ApiResponse(
            responseCode = "200", description = "Customer updated"
            responseCode = "500", description = "Error updating customer"
    )
    public CustomerModel updateCustomer(@PathVariable int id, @RequestBody CustomerModel customer) {
        return customerService.updateCustomer(id, customer);
    }
    /**
     * Delete a customer.
     *
     * @param id the ID of the customer
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a customer", description = "Delete a customer")
    @ApiResponse(
            responseCode = "200", description = "Customer deleted"
            responseCode = "500", description = "Error deleting customer"
    )
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }
}
