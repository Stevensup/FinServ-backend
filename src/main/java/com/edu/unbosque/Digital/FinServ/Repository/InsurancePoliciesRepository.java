package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.InsurancePoliciesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for InsurancePoliciesModel.
 * This interface provides methods to perform CRUD operations on InsurancePoliciesModel.
 */
@Repository
public interface InsurancePoliciesRepository extends JpaRepository<InsurancePoliciesModel, Integer> {
    List<InsurancePoliciesModel> findByCustomerId(int customerId);
}