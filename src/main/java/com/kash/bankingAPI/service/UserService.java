package com.kash.bankingAPI.service;

import com.kash.bankingAPI.entity.User;
import com.kash.bankingAPI.entity.security.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {
    User createUser(User user, Set<UserRole> userRoles);
    List<User> getUsers();


    User findUserByUsername(String username);

    User findUserByEmail(String email);
    boolean checkUsernameExists(String username);

}

