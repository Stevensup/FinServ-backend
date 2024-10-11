package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.Notification_PreferencesModel;
import com.edu.unbosque.Digital.FinServ.Repository.Notification_PreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing notification preferences.
 */
@Service
public class Notification_PreferencesService {

    @Autowired
    private Notification_PreferencesRepository notification_PreferencesRepository;

    /**
     * Creates a new notification preference.
     *
     * @param notification_Preferences the notification preference to create
     * @return the created notification preference
     */
    public Notification_PreferencesModel createNotification_Preferences(Notification_PreferencesModel notification_Preferences) {
        return notification_PreferencesRepository.save(notification_Preferences);
    }

    /**
     * Retrieves a notification preference by its ID.
     *
     * @param id the ID of the notification preference
     * @return the notification preference if found, or null if not found
     */
    public Notification_PreferencesModel getNotification_PreferencesById(int id) {
        return notification_PreferencesRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves all notification preferences.
     *
     * @return an iterable of all notification preferences
     */
    public Iterable<Notification_PreferencesModel> getAllNotification_Preferences() {
        return notification_PreferencesRepository.findAll();
    }

    /**
     * Updates an existing notification preference.
     *
     * @param id the ID of the notification preference to update
     * @param notification_Preferences the updated notification preference data
     * @return the updated notification preference
     * @throws RuntimeException if the notification preference with the specified ID is not found
     */
    public Notification_PreferencesModel updateNotification_Preferences(int id, Notification_PreferencesModel notification_Preferences) {
        if (notification_PreferencesRepository.existsById(id)) {
            notification_Preferences.setPreferenceId(id);
            return notification_PreferencesRepository.save(notification_Preferences);
        } else {
            throw new RuntimeException("Notification_Preferences not found with id " + id);
        }
    }

    /**
     * Deletes a notification preference by its ID.
     *
     * @param id the ID of the notification preference to delete
     * @throws RuntimeException if the notification preference with the specified ID is not found
     */
    public void deleteNotification_Preferences(int id) {
        if (notification_PreferencesRepository.existsById(id)) {
            notification_PreferencesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Notification_Preferences not found with id " + id);
        }
    }
}