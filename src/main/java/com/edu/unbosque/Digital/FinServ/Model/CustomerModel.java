package com.edu.unbosque.Digital.FinServ.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
//import java.util.List;

@Data
@Entity
@Getter
@Setter
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

    @Column(name = "username", length = 255)
    private String username;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @Column(name = "notification_preference_id")
    private Integer notificationPreferenceId;

//    @ManyToOne
//    @JoinColumn(name = "notification_preference_id", insertable = false, updatable = false)
//    private NotificationPreference notificationPreference;

    // @OneToMany(mappedBy = "customer")//
    // private List<Customer_ProductsModel> customerProducts;

    // @OneToMany(mappedBy = "customer")       
    // private List<Credit_CardModel> creditCard;





    @Override
    public String toString() {
        return "CustomerModel [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
                + email + ", phone=" + phone + ", address=" + address + ", registrationDate=" + registrationDate
                + "]";
    }

}