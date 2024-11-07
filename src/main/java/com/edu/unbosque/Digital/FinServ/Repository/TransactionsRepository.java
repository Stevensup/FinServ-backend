package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.TransactionsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for TransactionsModel.
 * This interface provides methods to perform CRUD operations on TransactionsModel.
 */
@Repository
public interface TransactionsRepository extends JpaRepository<TransactionsModel, Integer> {
    // Additional query methods can be added here
}