package com.edu.unbosque.Digital.FinServ.Controller.Auth;

import com.edu.unbosque.Digital.FinServ.Model.User_LoginsModel;
import com.edu.unbosque.Digital.FinServ.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody User_LoginsModel userLoginsModel) {
        return authService.login(userLoginsModel);
    }

    @PostMapping("/register")
    public String register(@RequestBody User_LoginsModel userLoginsModel) {
        return authService.register(userLoginsModel);
    }
}