package com.edu.unbosque.Digital.FinServ.Controller.Auth;

import com.edu.unbosque.Digital.FinServ.Model.User_LoginsModel;
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
    public ResponseEntity<String> login(@RequestBody User_LoginsModel loginDetails) {
        // Validar el formato del correo electrónico primero
        if (!EMAIL_PATTERN.matcher(loginDetails.getUsername()).matches()) {
            return ResponseEntity.badRequest().body("Invalid email address");
        }

        // Autenticar solo si el correo electrónico es válido
        boolean isAuthenticated = authService.authenticate(loginDetails.getUsername(), loginDetails.getPasswordHash());
        if (isAuthenticated) {
            // Send email notification
            String emailJson = "{ \"destinatario\": \"" + loginDetails.getUsername() + "\", \"asunto\": \"Login Notification\", \"cuerpo\": \"You have successfully logged in.\" }";
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
