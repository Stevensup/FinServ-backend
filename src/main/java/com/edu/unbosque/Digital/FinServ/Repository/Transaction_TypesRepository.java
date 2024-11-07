package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.Transaction_TypesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Transaction_TypesModel.
 * This interface provides methods to perform CRUD operations on Transaction_TypesModel.
 */
@Repository
public interface Transaction_TypesRepository extends JpaRepository<Transaction_TypesModel, Integer> {
    // Additional query methods can be added here
}