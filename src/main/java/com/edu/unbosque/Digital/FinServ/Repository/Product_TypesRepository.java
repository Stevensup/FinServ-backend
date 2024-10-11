package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.Product_TypesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Product_TypesModel.
 * This interface provides methods to perform CRUD operations on Product_TypesModel.
 */
@Repository
public interface Product_TypesRepository extends JpaRepository<Product_TypesModel, Integer> {
    // Additional query methods can be added here
}