package com.kash.bankingAPI.service.serviceImpl;

import com.kash.bankingAPI.entity.User;
import com.kash.bankingAPI.entity.security.UserRole;
import com.kash.bankingAPI.repository.RoleRepository;
import com.kash.bankingAPI.repository.UserRepository;
import com.kash.bankingAPI.service.AccountService;
import com.kash.bankingAPI.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private AccountService accountService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, @Lazy AccountService accountService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

        this.accountService = accountService;
    }

//    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, AccountService accountService) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.accountService = accountService;
//    }


    @Override
    public User createUser(User user, Set<UserRole> userRoles) {
        User localUser = userRepository.findUserByUsername(user.getUsername());

//        if(userRepository.existsByUsername(user.getUsername())){
            if(localUser != null){
            LOG.info("User with username {} already exist", user.getUsername());
        } else {
                String encryptedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(encryptedPassword);

////                for (UserRole ur : userRoles){
////                    roleRepository.save(ur.getRole());
////                }
                user.getUserRoles().addAll(userRoles);

                user.setPrimaryAccount(accountService.createPrimaryAccount());
                user.setSavingsAccount(accountService.createSavingsAccount());

            }
//        User user1 = new User();
//        user1.setName(user.getName());
//        user1.setUsername(user.getUsername());
//        user1.setEmail(user.getEmail());
//        user1.setPassword(passwordEncoder.encode(user.getPassword()));

        localUser = userRepository.save(user);
        return localUser;
    }


    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public boolean checkUsernameExists(String username) {
        if(userRepository.existsByUsername(username)) {
        return true;
        } else {
            return false;
        }

    }


//    public User checkUserExists(String newUsername, String newEmail) throws Exception {
//        User userByNewUsername = findUserByUsername(newUsername);
//        User userByNewEmail = findUserByEmail(newEmail);
//
//        if (userByNewUsername != null) {
//            throw new Exception("USERNAME_ALREADY_EXISTS");
//        }
//        if (userByNewEmail != null) {
//            throw new Exception("USERNAME_ALREADY_EXIST");
//        }
//        return null;
//    }
}
