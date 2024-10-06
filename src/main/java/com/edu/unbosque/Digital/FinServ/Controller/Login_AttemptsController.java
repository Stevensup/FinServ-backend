package com.edu.unbosque.Digital.FinServ.Controller;

import com.edu.unbosque.Digital.FinServ.Model.Login_attemptsModel;
import com.edu.unbosque.Digital.FinServ.Service.Login_attemptsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/LoginAttempts")
public class Login_AttemptsController {
    
    @Autowired
    private Login_attemptsService login_attemptsService;

    @PostMapping("/create")
    @Operation(summary = "Create a new Login_attempts", description = "Create a new Login_attempts")
    @ApiResponse(responseCode = "200", description = "Login_Attempts created")
    public Login_attemptsModel createLoginAttempts(@RequestBody Login_attemptsModel login_attempts){
        return login_attemptsService.createLogin_attempts(login_attempts);
    }

    @PostMapping("/{id}")
    @Operation(summary = "Get a Login_attempts by ID", description = "Get a Login_attempts by ID")
    @ApiResponse(responseCode = "200", description = "Login_Attempts found")
    public Optional<Login_attemptsModel> getLoginAttemptsById(@PathVariable int id){
        return login_attemptsService.getLoginAttemptsById(id);
    }

    @PostMapping("/all")
    @Operation(summary = "Get all Login_attempts", description = "Get all Login_attempts")
    @ApiResponse(responseCode = "200", description = "Login_Attempts retrieved")
    public List<Login_attemptsModel> getAllLoginAttempts(){
        return login_attemptsService.getAllLoginAttempts();
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Update a Login_attempts", description = "Update a Login_attempts")
    @ApiResponse(responseCode = "200", description = "Login_Attempts updated")
    public Login_attemptsModel updateLoginAttempts(@PathVariable int id, @RequestBody Login_attemptsModel loginAttempts){
        return login_attemptsService.updateLoginAttempts(id, loginAttempts);
    }

    @PostMapping("/delete/{id}")
    @Operation(summary = "Delete a Login_attempts", description = "Delete a Login_attempts")
    @ApiResponse(responseCode = "200", description = "Login_Attempts deleted")
    public void deleteLoginAttempts(@PathVariable int id){
        login_attemptsService.deleteLoginAttempts(id);
    }
}
