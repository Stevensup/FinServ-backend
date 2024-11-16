package com.edu.unbosque.Digital.FinServ.Controller;

import com.edu.unbosque.Digital.FinServ.Model.Product_TypesModel;
import com.edu.unbosque.Digital.FinServ.Service.Product_TypesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Transactional
@CrossOrigin(
        origins = {"http://localhost:8090","http://localhost:8080", "*"}
)
@RestController
@RequestMapping("/ProductTypes")
public class Product_TypesController {

    @Autowired
    private Product_TypesService product_typesService;

    /**
     * Create a new Product_Types.
     *
     * @param product_types data needed to create a new Product_Types
     * @return the created Product_Types
     */
    @PostMapping("/create")
    @Operation(summary = "Create a new Product_Types", description = "Create a new Product_Types")
    @ApiResponse(
            responseCode = "200", description = "Product_Types created successfully"
            responseCode = "400", description = "Invalid input"
    )
    public Product_TypesModel createProduct_Types(@RequestBody Product_TypesModel product_types) {
        return product_typesService.createProduct_Types(product_types);
    }

    /**
     * Get a Product_Types by ID.
     *
     * @param id the ID of the Product_Types
     * @return the Product_Types found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a Product_Types by id", description = "Get a Product_Types by id")
    @ApiResponse(
            responseCode = "200", description = "Product_Types found"
            responseCode = "400", description = "Invalid input"
    )
    public Optional<Product_TypesModel> getProductTypesById(@PathVariable int id) {
        return product_typesService.getProductTypesById(id);
    }

    /**
     * Get all Product_Types.
     *
     * @return all Product_Types
     */
    @GetMapping("/all")
    @Operation(summary = "Get all Product_Types", description = "Get all Product_Types")
    @ApiResponse(
            responseCode = "200", description = "Product_Types retrieved"
            responseCode = "400", description = "Invalid input"
    )
    public List<Product_TypesModel> getAllProductTypes() {
        return product_typesService.getAllProductTypes();
    }

    /**
     * Update a Product_Types.
     *
     * @param id the ID of the Product_Types
     * @param product_types data needed to update a Product_Types
     * @return the updated Product_Types
     */
    @PutMapping("/update/{id}")
    @Operation(summary = "Update a Product_Types ", description = "Update a Product_Types")
    @ApiResponse(
            responseCode = "200", description = "Product_Types updated"
            responseCode = "400", description = "Invalid input"
    )
    public Product_TypesModel updateProduct_Types(@PathVariable int id, @RequestBody Product_TypesModel product_types) {
        return product_typesService.updateProduct_Types(id, product_types);
    }

    /**
     * Delete a Product_Types.
     *
     * @param id the ID of the Product_Types
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a Product_Types", description = "Delete a Product_Types")
    @ApiResponse(
            responseCode = "200", description = "Product_Types deleted"
            responseCode = "400", description = "Invalid input"
    )
    public void deleteProduct_Types(@PathVariable int id) {
        product_typesService.deleteProduct_Types(id);
    }
}
