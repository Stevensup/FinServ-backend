package com.edu.unbosque.Digital.FinServ.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Data
@Entity
@Getter
@Setter
@Table(name = "notifications")
public class NotificationsModel {
    
    @Id
    @Column(name = "notification_id", nullable = false, updatable = false)
    private int notificationId;

    @Column(name = "customer_id", nullable = false, updatable = false)
    private int customerId;

    @Column(name = "notification_preference_id", nullable = false, updatable = false)
    private int notificationPreferenceId;

    @Column(name = "message", nullable = false, length = 100)
    private String message;

    @Column(name = "send_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date sendDate;

    @Override
    public String toString(){
        return "NotificationsModel [notificationId=" + notificationId + ", customerId=" + customerId + ",notificationPreferenceId="
        + notificationPreferenceId +", message="+ message +",sendDate= "+ sendDate
        +"]";
    }
}
