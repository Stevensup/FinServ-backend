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
@Table(name = "login_attempts")
public class Login_AttemptsModel {
    
    @Id
    @Column(name = "attempt_id", nullable = false, updatable = false)
    private int attemptId;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "username", length = 100)
    private String userName;

    @Column(name = "attempt_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date attemptDate;

    @Column(name = "success", nullable = false, updatable = false)
    private int success;

    @Override
    public String toString(){
        return "Login_AttempModel [attemptId=" + attemptId + ", customerId=" + customerId + ", userName=" 
        + userName + ", attemptDate=" +attemptDate+", success=" + success 
        +"]";
    }
}
