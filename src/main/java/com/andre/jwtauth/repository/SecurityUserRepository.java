package com.andre.jwtauth.repository;

import com.andre.jwtauth.security.model.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityUserRepository extends JpaRepository<SecurityUser, Long> {

    SecurityUser findByUsername(String username);
}
