package com.edu.unbosque.Digital.FinServ.Model;



/**
 * An entity LoginRequest that describes the Login Request Model
 * @Model LoginRequest
 */
public class LoginRequest {
    private String username;
    private String passwordHash;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

}