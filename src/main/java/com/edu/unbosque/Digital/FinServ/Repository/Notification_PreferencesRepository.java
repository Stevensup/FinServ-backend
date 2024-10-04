package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.Notification_PreferencesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class Notification_PreferencesRepository extends JpaRepository<Notification_PreferencesModel, Integer> {
    // Additional query methods can be added here
}