package com.edu.unbosque.Digital.FinServ.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


/**
 * An entity Login_AttemptsModel that describes the Login Attempts Model
 * @Model Login_AttemptsModel
 */
@Data
@Entity
@Getter
@Setter
@Table(name = "login_attempts")
public class Login_AttemptsModel {

    @Id
    @Column(name = "attempt_id", nullable = false, updatable = false)
    private int attemptId;

    @Column(name = "customer_id", nullable = false, insertable = false, updatable = false)
    private Integer customerId;

    @Column(name = "username", length = 100)
    private String userName;

    @Column(name = "attempt_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date attemptDate;

    @Column(name = "success", nullable = false, updatable = false)
    private int success;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    @Override
    public String toString(){
        return "Login_AttemptsModel [attemptId=" + attemptId + ", customerId=" + customerId + ", userName="
                + userName + ", attemptDate=" + attemptDate + ", success=" + success + "]";
    }
}