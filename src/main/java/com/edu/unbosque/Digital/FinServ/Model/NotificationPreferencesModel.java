package com.edu.unbosque.Digital.FinServ.Model;

import jakarta.persistence.*;
import lombok.Data;


/**
 * An entity NotificationPreferencesModel that describes the Notification Preferences Model
 * @Model NotificationPreferencesModel
 */
@Data
@Entity
@Table(name = "notification_preferences")
public class NotificationPreferencesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_id", nullable = false, updatable = false)
    private int preferenceId;

    @Column(name = "preference_name", nullable = false, length = 100)
    private String preferenceName;
}
