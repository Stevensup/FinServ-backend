package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.Login_AttemptsModel;
import com.edu.unbosque.Digital.FinServ.Repository.Login_AttemptsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Login_AttemptsService {
    
    @Autowired
    private Login_AttemptsRepository login_attemptsRepository;

    public Login_AttemptsModel createLogin_attempts(Login_AttemptsModel login_attempts){
        return login_attemptsRepository.save(login_attempts);
    }

    public Optional <Login_AttemptsModel> getLoginAttemptsById(int id){
        return login_attemptsRepository.findById(id);
    }

    public List<Login_AttemptsModel> getAllLoginAttempts(){
        return login_attemptsRepository.findAll();
    }

    public Login_AttemptsModel updateLoginAttempts(int id, Login_AttemptsModel loginAttempts){
        if (login_attemptsRepository.existsById(id)) {
            loginAttempts.setAttemptId(id);
            return login_attemptsRepository.save(loginAttempts);
        } else {
            throw new RuntimeException("Login attempts not found with id "+id);
        }
    }

    public void deleteLoginAttempts(int id){
        if (login_attemptsRepository.existsById(id)) {
            login_attemptsRepository.deleteById(id);
        }else {
            throw new RuntimeException("Login attempts not found with id"+id);
        }
    }
}
