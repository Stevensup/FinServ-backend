package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.Credit_CardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface repository for Credit_CardModel
 */
@Repository
public interface Credit_CardRepository extends JpaRepository<Credit_CardModel, Integer> {
    List<Credit_CardModel> findByCustomerId(int customerId);
}
