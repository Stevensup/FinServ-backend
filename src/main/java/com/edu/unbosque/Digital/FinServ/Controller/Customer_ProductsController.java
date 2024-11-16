package com.edu.unbosque.Digital.FinServ.Controller;

import com.edu.unbosque.Digital.FinServ.Model.Customer_ProductsModel;
import com.edu.unbosque.Digital.FinServ.Service.Customer_ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Transactional
@CrossOrigin(
        origins = {"http://localhost:8090", "http://localhost:8080", "*"}
)
@RestController
@RequestMapping("/CustomerProducts")
public class Customer_ProductsController {

    @Autowired
    private Customer_ProductsService customer_productsService;

    /**
     * Create a new Customer_Products.
     *
     * @param customer_products data needed to create a new Customer_Products
     * @return the created Customer_Products
     */

    @PostMapping("/create")
    @Operation(summary = "Create a new Customer_Products", description = "Create a new Customer_Products.")
    @ApiResponse(
            responseCode = "200", description = "Customer_Products created successfully."
    )
    public Customer_ProductsModel createCustomerProducts(@RequestBody Customer_ProductsModel customer_products) {
        return customer_productsService.createCustomer_Products(customer_products);
    }

    /**
     * Get a Customer_Products by ID.
     *
     * @param id the ID of the Customer_Products
     * @return the Customer_Products found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a Customer_Products by ID", description = "Get a Customer_Products by ID.")
    @ApiResponse(
            responseCode = "200", description = "Customer_Products found."
    )
    public Optional<Customer_ProductsModel> getCustomerProductsById(@PathVariable int id) {
        return customer_productsService.getCustomerProductsById(id);
    }
    /**
     * Get all Customer_Products.
     *
     * @return all Customer_Products
     */
    @GetMapping("/all")
    @Operation(summary = "Get all Customer_Products", description = "Get all Customer_Products.")
    @ApiResponse(
            responseCode = "200", description = "Customer_Products found."
    )
    public List<Customer_ProductsModel> getAllCustomerProducts() {
        return customer_productsService.getAllCustomerProducts();
    }

    /**
     * Update a Customer_Products.
     *
     * @param id the ID of the Customer_Products
     * @param customer_products data needed to update the Customer_Products
     * @return the updated Customer_Products
     */
    @PutMapping("/update/{id}")
    @Operation(summary = "Update a Customer_Products", description = "Update a Customer_Products.")
    @ApiResponse(
            responseCode = "200", description = "Customer_Products updated successfully."
    )
    public Customer_ProductsModel updateCustomerProducts(@PathVariable int id, @RequestBody Customer_ProductsModel customer_products) {
        return customer_productsService.updateCustomerProducts(id, customer_products);
    }

    /**
     * Delete a Customer_Products.
     *
     * @param id the ID of the Customer_Products
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a Customer_Products", description = "Delete a Customer_Products.")
    @ApiResponse(
            responseCode = "200", description = "Customer_Products deleted successfully."
    )
    public void deleteCustomerProducts(@PathVariable int id) {
        customer_productsService.deleteCustomerProducts(id);
    }
}