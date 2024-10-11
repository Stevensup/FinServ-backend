package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.User_LoginsModel;
import com.edu.unbosque.Digital.FinServ.Repository.User_LoginsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing user logins.
 */
@Service
public class User_LoginsService {

    @Autowired
    private User_LoginsRepository user_loginsRepository;

    /**
     * Creates a new user login.
     *
     * @param user_logins The user login model to create.
     * @return The created user login model.
     */
    public User_LoginsModel createUser_logins(User_LoginsModel user_logins){
        return user_loginsRepository.save(user_logins);
    }

    /**
     * Retrieves a user login by its ID.
     *
     * @param id The ID of the user login to retrieve.
     * @return An Optional containing the user login model if found, or empty if not found.
     */
    public Optional<User_LoginsModel> getUser_loginsById(int id){
        return user_loginsRepository.findById(id);
    }

    /**
     * Retrieves all user logins.
     *
     * @return A list of all user login models.
     */
    public List<User_LoginsModel> getAllUser_logins(){
        return user_loginsRepository.findAll();
    }

    /**
     * Updates an existing user login.
     *
     * @param id The ID of the user login to update.
     * @param user_logins The updated user login model.
     * @return The updated user login model.
     * @throws RuntimeException if the user login with the specified ID is not found.
     */
    public User_LoginsModel updateUser_logins(int id, User_LoginsModel user_logins){
        if(user_loginsRepository.existsById(id)){
            user_logins.setLoginId(id);
            return user_loginsRepository.save(user_logins);
        } else {
            throw new RuntimeException("User logins not found with id " + id);
        }
    }

    /**
     * Deletes a user login by its ID.
     *
     * @param id The ID of the user login to delete.
     * @throws RuntimeException if the user login with the specified ID is not found.
     */
    public void deleteUser_logins(int id){
        if (user_loginsRepository.existsById(id)) {
            user_loginsRepository.deleteById(id);
        }else {
            throw new RuntimeException("User logins not found with id" + id);
        }
    }

    /**
     * Authenticates a user login by username and password hash.
     *
     * @param username The username of the user login.
     * @param passwordHash The password hash of the user login.
     * @return true if the user login is authenticated, false otherwise.
     */
    public boolean authenticate(String username, String passwordHash) {
        return user_loginsRepository.findByUsernameAndPasswordHash(username, passwordHash).isPresent();
    }
}