package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // @Repository is optional in Spring Boot 3
public interface UserRepository extends JpaRepository<User, Long> {
    
    // deprecated native query style
    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    User findByUsernameNative(String username);
    
    // using deprecated JPQL query style with positional parameters
    @Query("SELECT u FROM User u WHERE u.email LIKE ?1")
    List<User> findByEmailPattern(String pattern);
    
    // using deprecated method naming pattern
    List<User> findByUsernameAndEmail(String username, String email);
} 