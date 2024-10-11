package com.edu.unbosque.Digital.FinServ.Repository;

import com.edu.unbosque.Digital.FinServ.Model.User_LoginsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface User_LoginsRepository extends JpaRepository<User_LoginsModel, Integer> {

    Optional<Object> findByUsernameAndPasswordHash(String username, String passwordHash);

}
