package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.InvestmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for InvestmentModel.
 * This interface provides methods to perform CRUD operations on InvestmentModel.
 */
@Repository
public interface InvestmentRepository extends JpaRepository<InvestmentModel, Integer> {
    List<InvestmentModel> findByCustomerId(int customerId);

    List<InvestmentModel> findByCustomerIdAndProductId(int customerId, int productId);
}
