package com.kash.bankingAPI.repository;

import com.kash.bankingAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    Boolean existsByUsername(String username);
}
