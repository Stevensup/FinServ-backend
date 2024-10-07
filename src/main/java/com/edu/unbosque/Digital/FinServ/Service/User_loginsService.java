package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.User_LoginsModel;
import com.edu.unbosque.Digital.FinServ.Repository.User_LoginsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class User_LoginsService {

    @Autowired
    private User_LoginsRepository user_loginsRepository;

    public User_LoginsModel createUser_logins(User_LoginsModel user_logins){
        return user_loginsRepository.save(user_logins);
    }

    public Optional<User_LoginsModel> getUser_loginsById(int id){
        return user_loginsRepository.findById(id);
    }

    public List<User_LoginsModel> getAllUser_logins(){
        return user_loginsRepository.findAll();
    }

    public User_LoginsModel updateUser_logins(int id, User_LoginsModel user_logins){
        if(user_loginsRepository.existsById(id)){
            user_logins.setLoginId(id);
            return user_loginsRepository.save(user_logins);
        } else {
            throw new RuntimeException("User logins not found with id " + id);
        }
    }

    public void deleteUser_logins(int id){
        if (user_loginsRepository.existsById(id)) {
            user_loginsRepository.deleteById(id);
        }else {
            throw new RuntimeException("User logins not found with id" + id);
        }
    }
}
