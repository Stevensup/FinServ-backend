package unbosque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCustomerById() {
        int customerID = 1;
        CustomerModel mockCustomer = new CustomerModel();
        mockCustomer.setCustomerId(customerID);
        mockCustomer.setFirstName("Juan");
        mockCustomer.setLastName("Andres");
        mockCustomer.setEmail("Juan@gmail.com");
        mockCustomer.setPhone("3108904521");
        mockCustomer.setAddress("Cra 40 Sur #20 -2");

        when(customerService.getCustomerById(customerID)).thenReturn(Optional.of(mockCustomer));

        Optional<CustomerModel> response = customerController.getCustomerById(customerID);

        assertEquals(true, response.isPresent(), "El cliente debería estar presente");
        assertEquals("Juan", response.get().getFirstName(), "El primer nombre del cliente debe ser 'Juan'");
        assertEquals("Andres", response.get().getLastName(), "El segundo nombre del cliente debe ser 'Andres'");
        assertEquals("Juan@gmail.com", response.get().getEmail(), "El correo electrónico debe ser 'Juan@gmail.com'");
        assertEquals("3108904521", response.get().getPhone(), "El número de contacto debe ser '3108904521'");
        assertEquals("Cra 40 Sur #20 -2", response.get().getAddress(), "La dirección debe ser 'Cra 40 Sur #20 -2'");
    }
}
