package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.NotificationPreferencesModel;
import com.edu.unbosque.Digital.FinServ.Repository.NotificationPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing notification preferences.
 */
@Service
public class NotificationPreferencesService {

    @Autowired
    private NotificationPreferencesRepository notificationPreferencesRepository;

    /**
     * Creates a new notification preference.
     *
     * @param notification_Preferences the notification preference to create
     * @return the created notification preference
     */
    public NotificationPreferencesModel createNotification_Preferences(NotificationPreferencesModel notification_Preferences) {
        return notificationPreferencesRepository.save(notification_Preferences);
    }

    /**
     * Retrieves a notification preference by its ID.
     *
     * @param id the ID of the notification preference
     * @return the notification preference if found, or null if not found
     */
    public NotificationPreferencesModel getNotification_PreferencesById(int id) {
        return notificationPreferencesRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves all notification preferences.
     *
     * @return an iterable of all notification preferences
     */
    public Iterable<NotificationPreferencesModel> getAllNotification_Preferences() {
        return notificationPreferencesRepository.findAll();
    }

    /**
     * Updates an existing notification preference.
     *
     * @param id the ID of the notification preference to update
     * @param notification_Preferences the updated notification preference data
     * @return the updated notification preference
     * @throws RuntimeException if the notification preference with the specified ID is not found
     */
    public NotificationPreferencesModel updateNotification_Preferences(int id, NotificationPreferencesModel notification_Preferences) {
        if (notificationPreferencesRepository.existsById(id)) {
            notification_Preferences.setPreferenceId(id);
            return notificationPreferencesRepository.save(notification_Preferences);
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
        if (notificationPreferencesRepository.existsById(id)) {
            notificationPreferencesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Notification_Preferences not found with id " + id);
        }
    }
}