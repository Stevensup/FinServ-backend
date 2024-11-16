package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.Transaction_LogsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Transaction_LogsModel.
 * This interface provides methods to perform CRUD operations on Transaction_LogsModel.
 */
@Repository
public interface Transaction_LogsRepository extends JpaRepository<Transaction_LogsModel, Integer> {
}