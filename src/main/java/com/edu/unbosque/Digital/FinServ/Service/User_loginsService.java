package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.User_loginsModel;
import com.edu.unbosque.Digital.FinServ.Repository.User_loginsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class User_loginsService {

    @Autowired
    private User_loginsRepository user_loginsRepository;

    public User_loginsModel createUser_logins(User_loginsModel user_logins){
        return user_loginsRepository.save(user_logins);
    }

    public Optional<User_loginsModel> getUser_loginsById(int id){
        return user_loginsRepository.findById(id);
    }

    public List<User_loginsModel> getAllUser_logins(){
        return user_loginsRepository.findAll();
    }

    public User_loginsModel updateUser_logins(int id, User_loginsModel user_logins){
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
