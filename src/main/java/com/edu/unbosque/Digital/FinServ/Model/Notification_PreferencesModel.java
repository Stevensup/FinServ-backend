package com.edu.unbosque.Digital.FinServ.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@Table(name = "notification_preferences")
public class Notification_PreferencesModel {

    @Id
    @Column(name = "preference_id", nullable = false, updatable = false)
    private int preferenceId;

    @Column(name = "preference_name", nullable = false, length = 100)
    private String preferenceName;

    @Override
    public String toString() {
        return "Notification_PreferencesModel [preferenceId=" + preferenceId + ", preferenceName=" + preferenceName + "]";
    }
}