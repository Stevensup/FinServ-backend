package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.NotificationsModel;
import com.edu.unbosque.Digital.FinServ.Repository.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationsService {
    
    @Autowired
    private NotificationsRepository notificationsRepository;

    public NotificationsModel createNotifications(NotificationsModel notifications){
        return notificationsRepository.save(notifications);
    }

    public NotificationsModel getNotificationsById(int id){
        return notificationsRepository.findById(id).orElse(null);
    }

    public Iterable<NotificationsModel> getAllNotifications(){
        return notificationsRepository.findAll();
    }

    public NotificationsModel updateNotifications(int id, NotificationsModel notifications){
        if(notificationsRepository.existsById(id)){
            notifications.setNotificationId(id);
            return notificationsRepository.save(notifications);
        } else {
            throw new RuntimeException("Notifications not found with id " + id);
        }
    }

    public void deleteNotifications(int id){
        if (notificationsRepository.existsById(id)){
            notificationsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Notifications not found with id" + id);
        }
    }
}

