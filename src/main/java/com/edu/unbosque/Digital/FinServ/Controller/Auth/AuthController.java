package com.edu.unbosque.Digital.FinServ.Controller.Auth;

import com.edu.unbosque.Digital.FinServ.Model.CustomerModel;
import com.edu.unbosque.Digital.FinServ.Model.LoginRequest;
import com.edu.unbosque.Digital.FinServ.Service.AuthService;
import com.edu.unbosque.Digital.FinServ.Service.CustomerService;
import com.edu.unbosque.Digital.FinServ.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@CrossOrigin(
        origins = {"http://localhost:8093","http://localhost:8080","*"}
)
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmailService emailService;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    @PostMapping("/login")
    @Operation(summary = "Login to the application", description = "Authenticate a user and return their customer ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated user and returned customer ID"),
            @ApiResponse(responseCode = "400", description = "Invalid email address"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "500", description = "Failed to send email")
    })
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String passwordHash = loginRequest.getPasswordHash();

        if (!EMAIL_PATTERN.matcher(username).matches()) {
            return ResponseEntity.badRequest().body("Invalid email address");
        }

        boolean isAuthenticated = authService.authenticate(username, passwordHash);
        if (isAuthenticated) {
            CustomerModel customer = customerService.getCustomerByEmail(username);
            String emailJson = "{ \"destinatario\": \"" + username + "\", \"asunto\": \"Login Notification\", \"cuerpo\": \"You have successfully logged in.\" }";
            try {
                emailService.enviarCorreo(emailJson);
                return ResponseEntity.ok(customer.getCustomerId());
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Failed to send email");
            }
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}