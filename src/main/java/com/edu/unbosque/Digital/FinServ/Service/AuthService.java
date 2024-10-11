package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.User_LoginsModel;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public String login(User_LoginsModel userLoginsModel) {
        // Lógica de autenticación
        return "Login successful";
    }

    public String register(User_LoginsModel userLoginsModel) {
        // Lógica de registro
        return "Registration successful";
    }
}
