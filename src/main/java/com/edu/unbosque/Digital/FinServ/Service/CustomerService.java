package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.CustomerModel;
import com.edu.unbosque.Digital.FinServ.Model.NotificationPreferencesModel;
import com.edu.unbosque.Digital.FinServ.Repository.CustomerRepository;
import com.edu.unbosque.Digital.FinServ.Repository.NotificationPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private NotificationPreferencesRepository notificationPreferencesRepository;

    public CustomerModel createCustomer(CustomerModel customer) {
        NotificationPreferencesModel notificationPreference = customer.getNotificationPreference();

        if (notificationPreference != null) {

            if (notificationPreference.getPreferenceId() == 0) {
                Optional<NotificationPreferencesModel> existingPreference =
                        notificationPreferencesRepository.findByPreferenceName(notificationPreference.getPreferenceName());


                if (existingPreference.isPresent()) {
                    customer.setNotificationPreference(existingPreference.get());
                } else {

                    notificationPreferencesRepository.save(notificationPreference);
                }
            } else {

                Optional<NotificationPreferencesModel> existingPreference =
                        notificationPreferencesRepository.findById(notificationPreference.getPreferenceId());

                if (existingPreference.isPresent()) {
                    customer.setNotificationPreference(existingPreference.get());
                }
            }
        }

        customer.setUsername(customer.getEmail());
        
        return customerRepository.save(customer);
    }



    public Optional<CustomerModel> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    public List<CustomerModel> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerModel updateCustomer(int id, CustomerModel customer) {
        if (customerRepository.existsById(id)) {
            customer.setCustomerId(id);
            return customerRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found with id " + id);
        }
    }

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