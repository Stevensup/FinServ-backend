package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.Login_AttemptsModel;
import com.edu.unbosque.Digital.FinServ.Repository.Login_AttemptsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing login attempts.
 */
@Service
public class Login_AttemptsService {

    @Autowired
    private Login_AttemptsRepository login_attemptsRepository;

    /**
     * Creates a new login attempt.
     *
     * @param login_attempts the login attempt to create
     * @return the created login attempt
     */
    public Login_AttemptsModel createLogin_attempts(Login_AttemptsModel login_attempts){
        return login_attemptsRepository.save(login_attempts);
    }

    /**
     * Retrieves a login attempt by its ID.
     *
     * @param id the ID of the login attempt
     * @return an Optional containing the login attempt if found, or empty if not found
     */
    public Optional<Login_AttemptsModel> getLoginAttemptsById(int id){
        return login_attemptsRepository.findById(id);
    }

    /**
     * Retrieves all login attempts.
     *
     * @return a list of all login attempts
     */
    public List<Login_AttemptsModel> getAllLoginAttempts(){
        return login_attemptsRepository.findAll();
    }

    /**
     * Updates an existing login attempt.
     *
     * @param id the ID of the login attempt to update
     * @param loginAttempts the updated login attempt data
     * @return the updated login attempt
     * @throws RuntimeException if the login attempt with the specified ID is not found
     */
    public Login_AttemptsModel updateLoginAttempts(int id, Login_AttemptsModel loginAttempts){
        if (login_attemptsRepository.existsById(id)) {
            loginAttempts.setAttemptId(id);
            return login_attemptsRepository.save(loginAttempts);
        } else {
            throw new RuntimeException("Login attempts not found with id " + id);
        }
    }

    /**
     * Deletes a login attempt by its ID.
     *
     * @param id the ID of the login attempt to delete
     * @throws RuntimeException if the login attempt with the specified ID is not found
     */
    public void deleteLoginAttempts(int id){
        if (login_attemptsRepository.existsById(id)) {
            login_attemptsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Login attempts not found with id " + id);
        }
    }
}