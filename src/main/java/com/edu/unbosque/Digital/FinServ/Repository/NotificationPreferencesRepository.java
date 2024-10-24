package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.NotificationPreferencesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Notification_PreferencesModel.
 * This interface provides methods to perform CRUD operations on Notification_PreferencesModel.
 */
@Repository
public interface NotificationPreferencesRepository extends JpaRepository<NotificationPreferencesModel, Integer> {

    Optional<NotificationPreferencesModel> findByPreferenceName(String preferenceName);
}