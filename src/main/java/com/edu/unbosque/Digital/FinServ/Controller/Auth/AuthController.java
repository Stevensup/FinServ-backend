// src/main/java/com/edu/unbosque/Digital/FinServ/Controller/Auth/AuthController.java
package com.edu.unbosque.Digital.FinServ.Controller.Auth;

import com.edu.unbosque.Digital.FinServ.Model.LoginRequest;
import com.edu.unbosque.Digital.FinServ.Service.AuthService;
import com.edu.unbosque.Digital.FinServ.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private EmailService emailService;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String passwordHash = loginRequest.getPasswordHash();

        // Validate email format first
        if (!EMAIL_PATTERN.matcher(username).matches()) {
            return ResponseEntity.badRequest().body("Invalid email address");
        }

        // Authenticate only if the email is valid
        boolean isAuthenticated = authService.authenticate(username, passwordHash);
        if (isAuthenticated) {
            // Send email notification
            String emailJson = "{ \"destinatario\": \"" + username + "\", \"asunto\": \"Login Notification\", \"cuerpo\": \"You have successfully logged in.\" }";
            try {
                emailService.enviarCorreo(emailJson);
                return ResponseEntity.ok("Login successful");
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Failed to send email");
            }
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}