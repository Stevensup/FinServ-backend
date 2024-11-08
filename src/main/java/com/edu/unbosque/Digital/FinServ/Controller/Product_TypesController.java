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
        origins = {"http://localhost:8090","http://localhost:8082", "*"}
)
@RestController
@RequestMapping("/ProductTypes")
public class Product_TypesController {

    @Autowired
    private Product_TypesService product_typesService;

    @PostMapping("/create")
    @Operation(
            summary = "Create a new Product_Types",
            description = "Create a new Product_Types"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Product_Types created successfully"
    )
    public Product_TypesModel createProduct_Types(@RequestBody Product_TypesModel product_types) {
        return product_typesService.createProduct_Types(product_types);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a Product_Types by id",
            description = "Get a Product_Types by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Product_Types found"
    )
    public Optional<Product_TypesModel> getProductTypesById(@PathVariable int id) {
        return product_typesService.getProductTypesById(id);
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get all Product_Types",
            description = "Get all Product_Types"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Product_Types retrieved"
    )
    public List<Product_TypesModel> getAllProductTypes() {
        return product_typesService.getAllProductTypes();
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update a Product_Types ",
            description = "Update a Product_Types"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Product_Types updated"
    )
    public Product_TypesModel updateProduct_Types(@PathVariable int id, @RequestBody Product_TypesModel product_types) {
        return product_typesService.updateProduct_Types(id, product_types);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete a Product_Types",
            description = "Delete a Product_Types"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Product_Types deleted"
    )
    public void deleteProduct_Types(@PathVariable int id) {
        product_typesService.deleteProduct_Types(id);
    }
}
