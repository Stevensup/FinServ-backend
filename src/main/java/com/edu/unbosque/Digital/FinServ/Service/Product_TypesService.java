package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.Product_TypesModel;
import com.edu.unbosque.Digital.FinServ.Repository.Product_TypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing product types.
 */
@Service
public class Product_TypesService {

    @Autowired
    private Product_TypesRepository products_typesRepository;

    /**
     * Creates a new product type.
     *
     * @param product_types the product type to create
     * @return the created product type
     */
    public Product_TypesModel createProduct_Types(Product_TypesModel product_types) {
        return products_typesRepository.save(product_types);
    }

    /**
     * Retrieves all product types.
     *
     * @return a list of all product types
     */
    public List<Product_TypesModel> getAllProductTypes() {
        return products_typesRepository.findAll();
    }

    /**
     * Retrieves a product type by its ID.
     *
     * @param id the ID of the product type
     * @return an Optional containing the product type if found, or empty if not found
     */
    public Optional<Product_TypesModel> getProductTypesById(int id) {
        return products_typesRepository.findById(id);
    }

    /**
     * Updates an existing product type.
     *
     * @param id the ID of the product type to update
     * @param product_types the updated product type data
     * @return the updated product type
     * @throws RuntimeException if the product type with the specified ID is not found
     */
    public Product_TypesModel updateProduct_Types(int id, Product_TypesModel product_types) {
        if (products_typesRepository.existsById(id)) {
            product_types.setProductTypeId(id);
            return products_typesRepository.save(product_types);
        } else {
            throw new RuntimeException("Product Types not found with id " + id);
        }
    }

    /**
     * Deletes a product type by its ID.
     *
     * @param id the ID of the product type to delete
     * @throws RuntimeException if the product type with the specified ID is not found
     */
    public void deleteProduct_Types(int id) {
        if (products_typesRepository.existsById(id)) {
            products_typesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product Types not found with id " + id);
        }
    }
}