package com.edu.unbosque.Digital.FinServ.Controller;

import com.edu.unbosque.Digital.FinServ.Model.User_LoginsModel;
import com.edu.unbosque.Digital.FinServ.Service.User_LoginsService;
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
@RequestMapping("/userLogins")
public class User_LoginsController {
    
    @Autowired
    private User_LoginsService user_loginsService;

    @PostMapping("/create")
    @Operation(summary = "Create a new user logins", description = "Create a new user logins")
    @ApiResponse(responseCode = "200", description = "User logins created")
    public User_LoginsModel createUser_logins(@RequestBody User_LoginsModel user_logins){
        return user_loginsService.createUser_logins(user_logins);
    }

    @PostMapping("/{id}")
    @Operation(summary = "Get a user logins by ID", description = "Get a user logins by ID")
    @ApiResponse(responseCode = "200", description = "User logins found")
    public Optional<User_LoginsModel> getUser_loginsById(@PathVariable int id){
        return user_loginsService.getUser_loginsById(id);
    }

    @PostMapping("/all")
    @Operation(summary = "Get all logins", description = "Get all logins")
    @ApiResponse(responseCode = "200", description = "User logins retrieved")
    public List<User_LoginsModel> getAllUser_logins(){
        return user_loginsService.getAllUser_logins();
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Update a logins", description = "Update a logins")
    @ApiResponse(responseCode = "200", description = "User updated")
    public User_LoginsModel updateUser_logins(@PathVariable int id, @RequestBody User_LoginsModel user_logins){
        return user_loginsService.updateUser_logins(id, user_logins);
    }

    @PostMapping("/delete/{id}")
    @Operation(summary = "Delete a logins", description = "Delete a logins")
    @ApiResponse(responseCode = "200", description = "User deleted")
    public void deleteUser_logins(@PathVariable int id){
        user_loginsService.deleteUser_logins(id);
    }

}
