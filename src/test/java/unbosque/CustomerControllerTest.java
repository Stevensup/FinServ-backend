package unbosque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.edu.unbosque.Digital.FinServ.Controller.CustomerController;
import com.edu.unbosque.Digital.FinServ.Model.CustomerModel;
import com.edu.unbosque.Digital.FinServ.Service.CustomerService;

public class CustomerControllerTest {
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks before each test
    }

    @Test
    void testGetCustomerById() {
        // Test data for a client
        int customerID = 1;
        CustomerModel mockCustomer = new CustomerModel();
        mockCustomer.setCustomerId(customerID);
        mockCustomer.setFirstName("Juan");
        mockCustomer.setLastName("Andres");
        mockCustomer.setEmail("Juan@gmail.com");
        mockCustomer.setPhone("3108904521");
        mockCustomer.setAddress("Cra 40 Sur #20 -2");

        // Configuring simulated service behavior
        when(customerService.getCustomerById(customerID)).thenReturn(Optional.of(mockCustomer));

        // Call to the controller method we want to test
        Optional<CustomerModel> response = customerController.getCustomerById(customerID);

        // Verification of the expected response
        assertEquals(true, response.isPresent(), "The client should be present");
        assertEquals("Juan", response.get().getFirstName(), "The client's first name must be 'Juan'");
        assertEquals("Andres", response.get().getLastName(), "The client's middle name must be 'Andres'");
        assertEquals("Juan@gmail.com", response.get().getEmail(), "The customer's email must be 'Juan@gmail.com'");
        assertEquals("3108904521", response.get().getPhone(), "The contact number should be '3108904521'");
        assertEquals("Cra 40 Sur #20 -2", response.get().getAddress(), "The client's address must be 'Cra 40 Sur #20 -2'");
    }
}
