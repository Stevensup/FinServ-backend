package unbosque;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.edu.unbosque.Digital.FinServ.Controller.Auth.AuthController;
import com.edu.unbosque.Digital.FinServ.Model.LoginRequest;
import com.edu.unbosque.Digital.FinServ.Service.AuthService;
import com.edu.unbosque.Digital.FinServ.Service.CustomerService;
import com.edu.unbosque.Digital.FinServ.Service.EmailService;

class AuthControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private CustomerService customerService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_InvalidCredentials() {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("test@email.com");
        loginRequest.setPasswordHash("ystdy3");

        when(authService.authenticate("test@imail.com", "ystdy3")).thenReturn(false);

        ResponseEntity<?> response = authController.login(loginRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid credentials", response.getBody());

    }
}