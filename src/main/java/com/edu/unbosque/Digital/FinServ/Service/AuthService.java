package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.User_LoginsModel;
import com.edu.unbosque.Digital.FinServ.Repository.User_LoginsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for handling authentication.
 */
@Service
public class AuthService {

    @Autowired
    private User_LoginsRepository userLoginsRepository;

    /**
     * Authenticates a user based on username and password hash.
     *
     * @param username the username of the user
     * @param passwordHash the hashed password of the user
     * @return true if the user is authenticated, false otherwise
     */
    public boolean authenticate(String username, String passwordHash) {
        Optional<Object> user = userLoginsRepository.findByUsernameAndPasswordHash(username, passwordHash);
        return user.isPresent();
    }
}