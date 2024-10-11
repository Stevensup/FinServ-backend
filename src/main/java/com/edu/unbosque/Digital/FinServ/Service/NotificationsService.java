package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.NotificationsModel;
import com.edu.unbosque.Digital.FinServ.Repository.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing notifications.
 */
@Service
public class NotificationsService {

    @Autowired
    private NotificationsRepository notificationsRepository;

    /**
     * Creates a new notification.
     *
     * @param notifications the notification to create
     * @return the created notification
     */
    public NotificationsModel createNotifications(NotificationsModel notifications){
        return notificationsRepository.save(notifications);
    }

    /**
     * Retrieves a notification by its ID.
     *
     * @param id the ID of the notification
     * @return the notification if found, or null if not found
     */
    public NotificationsModel getNotificationsById(int id){
        return notificationsRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves all notifications.
     *
     * @return an iterable of all notifications
     */
    public Iterable<NotificationsModel> getAllNotifications(){
        return notificationsRepository.findAll();
    }

    /**
     * Updates an existing notification.
     *
     * @param id the ID of the notification to update
     * @param notifications the updated notification data
     * @return the updated notification
     * @throws RuntimeException if the notification with the specified ID is not found
     */
    public NotificationsModel updateNotifications(int id, NotificationsModel notifications){
        if(notificationsRepository.existsById(id)){
            notifications.setNotificationId(id);
            return notificationsRepository.save(notifications);
        } else {
            throw new RuntimeException("Notifications not found with id " + id);
        }
    }

    /**
     * Deletes a notification by its ID.
     *
     * @param id the ID of the notification to delete
     * @throws RuntimeException if the notification with the specified ID is not found
     */
    public void deleteNotifications(int id){
        if (notificationsRepository.existsById(id)){
            notificationsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Notifications not found with id " + id);
        }
    }
}