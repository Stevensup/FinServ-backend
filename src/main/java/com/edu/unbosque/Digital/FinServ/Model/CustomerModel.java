package com.edu.unbosque.Digital.FinServ.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;


/**
 * An entity CustomerModel that describes the Customer Model
 * @Model CustomerModel
 */
@Data
@Entity
@Table(name = "customers")
public class CustomerModel {

    @Id
    @Column(name = "customer_id", nullable = false, updatable = false)
    private int customerId;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @Column(name = "username", nullable = false, unique = true, length = 255)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notification_preference_id", referencedColumnName = "preference_id")
    private NotificationPreferencesModel notificationPreference;
}