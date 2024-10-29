package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.Credit_CardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Credit_CardModel.
 * This interface provides methods to perform CRUD operations on Credit_CardModel.
 */
@Repository
public interface Credit_CardRepository extends JpaRepository<Credit_CardModel, Integer> {
    // Additional query methods can be added here
}