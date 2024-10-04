package com.edu.unbosque.Digital.FinServ.Service;


import com.edu.unbosque.Digital.FinServ.Model.Notification_PreferencesModel;
import com.edu.unbosque.Digital.FinServ.Repository.Notification_PreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Notification_PreferencesService {

    @Autowired
    private Notification_PreferencesRepository notification_PreferencesRepository;

    public Notification_PreferencesModel createNotification_Preferences(Notification_PreferencesModel notification_Preferences) {
        return notification_PreferencesRepository.save(notification_Preferences);
    }

    public Notification_PreferencesModel getNotification_PreferencesById(int id) {
        return notification_PreferencesRepository.findById(id).orElse(null);
    }

    public Iterable<Notification_PreferencesModel> getAllNotification_Preferences() {
        return notification_PreferencesRepository.findAll();
    }

    public Notification_PreferencesModel updateNotification_Preferences(int id, Notification_PreferencesModel notification_Preferences) {
        if (notification_PreferencesRepository.existsById(id)) {
            notification_Preferences.setPreferenceId(id);
            return notification_PreferencesRepository.save(notification_Preferences);
        } else {
            throw new RuntimeException("Notification_Preferences not found with id " + id);
        }
    }

    public void deleteNotification_Preferences(int id) {
        if (notification_PreferencesRepository.existsById(id)) {
            notification_PreferencesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Notification_Preferences not found with id " + id);
        }
    }


}
