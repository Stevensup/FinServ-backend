package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.Customer_ProductsModel;
import com.edu.unbosque.Digital.FinServ.Repository.Customer_ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing customer products.
 */
@Service
public class Customer_ProductsService {

    @Autowired
    private Customer_ProductsRepository customer_productsRepository;

    /**
     * Creates a new customer product.
     *
     * @param customer_products the customer product to create
     * @return the created customer product
     */
    public Customer_ProductsModel createCustomer_Products(Customer_ProductsModel customer_products) {
        return customer_productsRepository.save(customer_products);
    }

    /**
     * Retrieves a customer product by its ID.
     *
     * @param id the ID of the customer product
     * @return an Optional containing the customer product if found, or empty if not found
     */
    public Optional<Customer_ProductsModel> getCustomerProductsById(int id) {
        return customer_productsRepository.findById(id);
    }

    /**
     * Retrieves all customer products.
     *
     * @return a list of all customer products
     */
    public List<Customer_ProductsModel> getAllCustomerProducts() {
        return customer_productsRepository.findAll();
    }

    /**
     * Updates an existing customer product.
     *
     * @param id the ID of the customer product to update
     * @param customer_products the updated customer product data
     * @return the updated customer product
     * @throws RuntimeException if the customer product with the specified ID is not found
     */
    public Customer_ProductsModel updateCustomerProducts(int id, Customer_ProductsModel customer_products) {
        if (customer_productsRepository.existsById(id)) {
            customer_products.setCustomerProductId(id);
            return customer_productsRepository.save(customer_products);
        } else {
            throw new RuntimeException("Customer_Products not found with id " + id);
        }
    }

    /**
     * Deletes a customer product by its ID.
     *
     * @param id the ID of the customer product to delete
     * @throws RuntimeException if the customer product with the specified ID is not found
     */
    public void deleteCustomerProducts(int id) {
        if (customer_productsRepository.existsById(id)) {
            customer_productsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Customer_Products not found with id " + id);
        }
    }
}