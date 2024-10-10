package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.Customer_ProductsModel;
import com.edu.unbosque.Digital.FinServ.Repository.Customer_ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class Customer_ProductsService {

    @Autowired
    private Customer_ProductsRepository customer_productsRepository;

    public Customer_ProductsModel createCustomer_products(Customer_ProductsModel customer_products) {
        return customer_productsRepository.save(customer_products);
    }

    public Optional<Customer_ProductsModel> getCustomerProductsById(int id) {
        return customer_productsRepository.findById(id);
    }

    public List<Customer_ProductsModel> getAllCustomerProducts() {
        return customer_productsRepository.findAll();
    }

    public Customer_ProductsModel updateCustomerProducts(int id, Customer_ProductsModel customer_products) {
        if (customer_productsRepository.existsById(id)) {
            customer_products.setCustomerProductId(id);
            return customer_productsRepository.save(customer_products);
        } else {
            throw new RuntimeException("Customer_Products not found with id " + id);
        }
    }

    public void deleteCustomerProducts(int id) {
        if (customer_productsRepository.existsById(id)) {
            customer_productsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Customer_Products not found with id " + id);
        }
    }
}