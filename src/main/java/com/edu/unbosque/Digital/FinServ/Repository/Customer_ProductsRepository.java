package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.Customer_ProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Customer_ProductsModel.
 * This interface provides methods to perform CRUD operations on Customer_ProductsModel.
 */
@Repository
public interface Customer_ProductsRepository extends JpaRepository<Customer_ProductsModel, Integer> {
    // Additional query methods can be added here
}