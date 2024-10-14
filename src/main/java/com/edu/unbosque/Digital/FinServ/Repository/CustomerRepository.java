package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for CustomerModel.
 * This interface provides methods to perform CRUD operations on CustomerModel.
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {
    CustomerModel findByEmail(String username);
    // Additional query methods can be added here
}