package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.PqrsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for PqrsModel.
 * This interface provides methods to perform CRUD operations on PqrsModel.
 */
@Repository
public interface PqrsRepository extends JpaRepository<PqrsModel, Integer> {
    List<PqrsModel> findByCustomerId(int customerId);
}
