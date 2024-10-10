package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.Transaction_LogsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Transaction_LogsRepository extends JpaRepository<Transaction_LogsModel, Integer>{
    // Additional query methods can be added here
}
