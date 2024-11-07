package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.User_LoginsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for User_LoginsModel.
 * This interface provides methods to perform CRUD operations on User_LoginsModel.
 */
@Repository
public interface User_LoginsRepository extends JpaRepository<User_LoginsModel, Integer> {

    /**
     * Finds a user by username and password hash.
     *
     * @param username the username of the user
     * @param passwordHash the hashed password of the user
     * @return an Optional containing the user if found, or empty if not found
     */
    Optional<Object> findByUsernameAndPasswordHash(String username, String passwordHash);

}