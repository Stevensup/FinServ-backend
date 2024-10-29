package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.NotificationsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for NotificationsModel.
 * This interface provides methods to perform CRUD operations on NotificationsModel.
 */
@Repository
public interface NotificationsRepository extends JpaRepository<NotificationsModel, Integer> {
    // Additional query methods can be added here
}