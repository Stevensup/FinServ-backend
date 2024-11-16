package com.edu.unbosque.Digital.FinServ.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


/**
 * An entity NotificationsModel that describes the Notifications Model
 * @Model NotificationsModel
 */
@Data
@Entity
@Getter
@Setter
@Table(name = "notifications")
public class NotificationsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id", nullable = false, updatable = false)
    private int notificationId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customerId;

    @Column(name = "notification_preference_id", nullable = false)
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