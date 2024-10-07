package com.edu.unbosque.Digital.FinServ.Controller;

import com.edu.unbosque.Digital.FinServ.Model.Financial_ProductsModel;
import com.edu.unbosque.Digital.FinServ.Service.Financial_ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


@Transactional
@CrossOrigin(
    origins = {"http://localhost:8090", "http://localhost:8080", "*"}
)

@RestController
@RequestMapping("/Financial_Products")

public class Financial_ProductsController {
    
    @Autowired
    private Financial_ProductsService financial_productsService;

    @PostMapping("/create")
    @Operation(
        summary = "Create a new Financial_Products", 
        description = "Create a new Financial_Products"
    )

    @ApiResponse(
        responseCode = "200",
        description = "Financial_Products created successfully"
    )

    public Financial_ProductsModel createFinancialProducts(@RequestBody Financial_ProductsModel financial_products) {
        return financial_productsService.createFinancial_Products(financial_products);
    }

    @PostMapping({"/{id}"})
    @Operation(
        summary = "Get a Financial_Products by id",
        description = "Get a Financial_Products by id"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Financial_Products found"
    )

    public Optional<Financial_ProductsModel> getFinancialProductsById(@PathVariable int id) {
        return financial_productsService.getFinancialProductsById(id);
    }

    @PostMapping({"/all"})
    @Operation(
        summary = "Get all Financial_Products",
        description = "Get all Financial_Products"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Financial_Products retrieved"
    )

    public List<Financial_ProductsModel> getAllFinancialProducts() {
        return financial_productsService.getAllFinancialProducts();
    }

    @PostMapping({"/update/{id}"})
    @Operation(
        summary = "Update a Financial_Products",
        description = "Update a Financial_Products"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Financial_Products updated"
    )

    public Financial_ProductsModel updateFinancialProducts(@PathVariable int id, @RequestBody Financial_ProductsModel financial_products) {
        return financial_productsService.updateFinancialProducts(id, financial_products);
    }

    @PostMapping({"/delete/{id}"})
    @Operation(
        summary = "Delete a Financial_Products",
        description = "Delete a Financial_Products"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Financial_Products deleted"
    )

    public void deleteFinancialProducts(@PathVariable int id) {
        financial_productsService.deleteFinancialProducts(id);
    }

}
