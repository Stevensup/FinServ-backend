package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.Login_AttemptsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Login_AttemptsModel.
 * This interface provides methods to perform CRUD operations on Login_AttemptsModel.
 */
@Repository
public interface Login_AttemptsRepository extends JpaRepository<Login_AttemptsModel, Integer> {
    // Additional query methods can be added here
}