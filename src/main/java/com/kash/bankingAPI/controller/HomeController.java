package com.kash.bankingAPI.controller;

import com.kash.bankingAPI.entity.PrimaryAccount;
import com.kash.bankingAPI.entity.SavingsAccount;
import com.kash.bankingAPI.entity.User;
import com.kash.bankingAPI.entity.security.UserRole;
import com.kash.bankingAPI.repository.RoleRepository;
import com.kash.bankingAPI.repository.UserRepository;
import com.kash.bankingAPI.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    private UserRepository userRepository;
    private UserService userService;
    private RoleRepository roleRepository;

    public HomeController(UserRepository userRepository, UserService userService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) throws Exception {
        if(userRepository.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("User exist", HttpStatus.BAD_REQUEST);
        } else{
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleRepository.findByName("ROLE_USER")));
            userService.createUser(user, userRoles);
            return new ResponseEntity<>("User has been created successfully", HttpStatus.OK);
            //            return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
        }
    }

    @RequestMapping("/userfront")
    public ResponseEntity<?> userFront(Principal principal){
        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();

        Map<String, Object> result = new HashMap<String,Object>();
        result.put("PrimaryAccount",primaryAccount);
        result.put("SavingsAccount",savingsAccount);

        // Add any additional props that you want to add
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);

//        return new ResponseEntity<>(user.getPrimaryAccount().getAccountBalance(), HttpStatus.OK);
//        return new ResponseEntity<>(user.getSavingsAccount().getAccountBalance(), HttpStatus.OK);
    }


}
