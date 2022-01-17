package com.kash.bankingAPI.controller;

import com.kash.bankingAPI.entity.PrimaryAccount;
import com.kash.bankingAPI.entity.SavingsAccount;
import com.kash.bankingAPI.entity.User;
import com.kash.bankingAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private UserService userService;

    @RequestMapping("/primaryaccount")
    public ResponseEntity<Object> primaryAccount(Principal principal){
        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        return new ResponseEntity<>(primaryAccount, HttpStatus.OK);
    }

    @RequestMapping("/savingsaccount")
    public ResponseEntity<Object> savingsAccount (Principal principal){
        User user = userService.findByUsername(principal.getName());
        SavingsAccount savingsAccount = user.getSavingsAccount();
        return new ResponseEntity<>(savingsAccount, HttpStatus.OK);
    }
}
