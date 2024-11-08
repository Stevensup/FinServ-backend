package unbosque;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.unbosque.Digital.FinServ.Model.CustomerModel;
import com.edu.unbosque.Digital.FinServ.Model.LoginRequest;
import com.edu.unbosque.Digital.FinServ.Service.AuthService;
import com.edu.unbosque.Digital.FinServ.Service.CustomerService;
import com.edu.unbosque.Digital.FinServ.Service.EmailService;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:8093","http://localhost:8080","*"})
public class AuthControllerTest {
    private  AuthService authService;
    private  CustomerService customerService;
    private EmailService emailService;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    // Constructor for testing
    public void AuthController(AuthService authService, CustomerService customerService, EmailService emailService) {
        this.authService = authService;
        this.customerService = customerService;
        this.emailService = emailService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String passwordHash = loginRequest.getPasswordHash();

        if (!EMAIL_PATTERN.matcher(username).matches()) {
            return ResponseEntity.badRequest().body("Invalid email address");
        }

        boolean isAuthenticated = authService.authenticate(username, passwordHash);
        if (isAuthenticated) {
            CustomerModel customer = customerService.getCustomerByEmail(username);
            String emailJson = "{ \"addressee\": \"" + username + "\", \"affair\": \"Login Notification\", \"body\": \"You have successfully logged in.\" }";
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
