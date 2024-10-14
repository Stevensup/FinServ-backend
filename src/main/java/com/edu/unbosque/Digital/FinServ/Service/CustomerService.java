package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.CustomerModel;
import com.edu.unbosque.Digital.FinServ.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing customers.
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Creates a new customer.
     *
     * @param customer the customer to create
     * @return the created customer
     */
    public CustomerModel createCustomer(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    /**
     * Retrieves a customer by its ID.
     *
     * @param id the ID of the customer
     * @return an Optional containing the customer if found, or empty if not found
     */
    public Optional<CustomerModel> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    /**
     * Retrieves all customers.
     *
     * @return a list of all customers
     */
    public List<CustomerModel> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Updates an existing customer.
     *
     * @param id the ID of the customer to update
     * @param customer the updated customer data
     * @return the updated customer
     * @throws RuntimeException if the customer with the specified ID is not found
     */
    public CustomerModel updateCustomer(int id, CustomerModel customer) {
        if (customerRepository.existsById(id)) {
            customer.setCustomerId(id);
            return customerRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found with id " + id);
        }
    }

    /**
     * Deletes a customer by its ID.
     *
     * @param id the ID of the customer to delete
     * @throws RuntimeException if the customer with the specified ID is not found
     */
    public void deleteCustomer(int id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Customer not found with id " + id);
        }
    }

    public CustomerModel getCustomerByEmail(String username) {
        return customerRepository.findByEmail(username);
    }
}