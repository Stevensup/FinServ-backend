package com.edu.unbosque.Digital.FinServ.Controller;

import com.edu.unbosque.Digital.FinServ.Model.Login_AttemptsModel;
import com.edu.unbosque.Digital.FinServ.Service.Login_AttemptsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Transactional
@CrossOrigin(
    origins = { "http://localhost:8090", "http://localhost:8080", "*" }
)
@RestController
@RequestMapping("/LoginAttempts")

public class Login_AttemptsController {
    
    @Autowired
    private Login_AttemptsService login_attemptsService;

    /**
     * Create a new Login_Attempts.
     *
     * @param login_attempts data needed to create a new Login_Attempts
     * @return the created Login_Attempts
     */
    @PostMapping("/create")
    @Operation(summary = "Create a new Login_attempts", description = "Create a new Login_attempts")
    @ApiResponse(
        responseCode = "200", description = "Login_Attempts created"
    )
    public Login_AttemptsModel createLoginAttempts(@RequestBody Login_AttemptsModel login_attempts){
        return login_attemptsService.createLogin_attempts(login_attempts);
    }

    /**
     * Get a Login_Attempts by ID.
     *
     * @param id the ID of the Login_Attempts
     * @return the Login_Attempts found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a Login_attempts by ID", description = "Get a Login_attempts by ID")
    @ApiResponse(
        responseCode = "200", description = "Login_Attempts found"
    )
    public Optional<Login_AttemptsModel> getLoginAttemptsById(@PathVariable int id){
        return login_attemptsService.getLoginAttemptsById(id);
    }

    /**
     * Get all Login_Attempts.
     *
     * @return all Login_Attempts
     */
    @GetMapping("/all")
    @Operation(summary = "Get all Login_attempts", description = "Get all Login_attempts")
    @ApiResponse(
        responseCode = "200", description = "Login_Attempts retrieved"
    )

    public List<Login_AttemptsModel> getAllLoginAttempts(){
        return login_attemptsService.getAllLoginAttempts();
    }

    /**
     * Update a Login_Attempts.
     *
     * @param id the ID of the Login_Attempts
     * @param loginAttempts data needed to update a Login_Attempts
     * @return the updated Login_Attempts
     */
    @PutMapping("/update/{id}")
    @Operation(summary = "Update a Login_attempts", description = "Update a Login_attempts")
    @ApiResponse(
        responseCode = "200", description = "Login_Attempts updated"
    )

    public Login_AttemptsModel updateLoginAttempts(@PathVariable int id, @RequestBody Login_AttemptsModel loginAttempts){
        return login_attemptsService.updateLoginAttempts(id, loginAttempts);
    }

    /**
     * Delete a Login_Attempts.
     *
     * @param id the ID of the Login_Attempts
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a Login_attempts", description = "Delete a Login_attempts")
    @ApiResponse(
        responseCode = "200", description = "Login_Attempts deleted"
    )

    public void deleteLoginAttempts(@PathVariable int id){
        login_attemptsService.deleteLoginAttempts(id);
    }
}
