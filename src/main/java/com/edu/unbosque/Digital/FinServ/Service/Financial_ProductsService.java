package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.Financial_ProductsModel;
import com.edu.unbosque.Digital.FinServ.Repository.Financial_ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing financial products.
 */
@Service
public class Financial_ProductsService {

    @Autowired
    private Financial_ProductsRepository financial_productsRepository;

    /**
     * Creates a new financial product.
     *
     * @param financial_products the financial product to create
     * @return the created financial product
     */
    public Financial_ProductsModel createFinancial_Products(Financial_ProductsModel financial_products) {
        return financial_productsRepository.save(financial_products);
    }

    /**
     * Retrieves all financial products.
     *
     * @return a list of all financial products
     */
    public List<Financial_ProductsModel> getAllFinancialProducts(){
        return financial_productsRepository.findAll();
    }

    /**
     * Retrieves a financial product by its ID.
     *
     * @param id the ID of the financial product
     * @return an Optional containing the financial product if found, or empty if not found
     */
    public Optional<Financial_ProductsModel> getFinancialProductsById(int id) {
        return financial_productsRepository.findById(id);
    }

    /**
     * Updates an existing financial product.
     *
     * @param id the ID of the financial product to update
     * @param financial_products the updated financial product data
     * @return the updated financial product
     * @throws RuntimeException if the financial product with the specified ID is not found
     */
    public Financial_ProductsModel updateFinancialProducts(int id, Financial_ProductsModel financial_products) {
        if (financial_productsRepository.existsById(id)) {
            return financial_productsRepository.save(financial_products);
        } else {
            throw new RuntimeException("Financial_Products not found with id " + id);
        }
    }

    /**
     * Deletes a financial product by its ID.
     *
     * @param id the ID of the financial product to delete
     * @throws RuntimeException if the financial product with the specified ID is not found
     */
    public void deleteFinancialProducts(int id) {
        if (financial_productsRepository.existsById(id)) {
            financial_productsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Financial_Products not found with id " + id);
        }
    }
}