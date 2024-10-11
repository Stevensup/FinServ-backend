package com.edu.unbosque.Digital.FinServ.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Data
@Entity
@Getter
@Setter
@Table(name = "user_logins")
public class User_LoginsModel {

    @JsonIgnore
    @Id
    @Column(name = "login_id", nullable = false, updatable = false)
    private int loginId;

    @JsonIgnore
    @Column(name = "customer_id", nullable = false, updatable = false)
    private int customerId;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @JsonIgnore
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @JsonIgnore
    @Column(name = "last_login", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date lastLogin;

//    @ManyToOne
//    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
//    private  Customer customer;

    @Override
    public String toString(){
        return "User_loginsModel [loginId=" + loginId + ", customerId=" + customerId + ", username=" + username + ", passwordHash="
        + passwordHash + ", createdAt=" + createdAt + ", lastLogin=" + lastLogin 
        + "]";
    }
}
