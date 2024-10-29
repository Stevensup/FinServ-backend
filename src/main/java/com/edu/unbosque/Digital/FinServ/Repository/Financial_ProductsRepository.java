package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.Financial_ProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Financial_ProductsModel.
 * This interface provides methods to perform CRUD operations on Financial_ProductsModel.
 */
@Repository
public interface Financial_ProductsRepository extends JpaRepository<Financial_ProductsModel, Integer> {
    // Additional query methods can be added here
}