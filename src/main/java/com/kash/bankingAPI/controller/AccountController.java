package com.kash.bankingAPI.controller;

import com.kash.bankingAPI.entity.PrimaryAccount;
import com.kash.bankingAPI.entity.SavingsAccount;
import com.kash.bankingAPI.entity.User;
import com.kash.bankingAPI.service.AccountService;
import com.kash.bankingAPI.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

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

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestParam("amount") String amount,
                                     @RequestParam("accountType") String accountType,
                                     Principal principal){
        accountService.deposit(Double.parseDouble(amount), accountType, principal);
        return new ResponseEntity<>("Amount has been deposit successfully", HttpStatus.OK);

    }
}
