package com.edu.unbosque.Digital.FinServ.Controller;


import com.edu.unbosque.Digital.FinServ.Model.CustomerModel;
import com.edu.unbosque.Digital.FinServ.Service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    @Operation(summary = "Create a new customer", description = "Create a new customer")
    @ApiResponse(responseCode = "200", description = "Customer created")
    public CustomerModel createCustomer(@RequestBody CustomerModel customer) {
        return customerService.createCustomer(customer);
    }


    
}
