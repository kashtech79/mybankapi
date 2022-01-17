package com.kash.bankingAPI.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountController {

    @RequestMapping("/primaryaccount")
    public String primaryAccount(){
        return primaryAccount();
    }

    @RequestMapping("/savingsaccount")
    public String savingsAccount(){
        return savingsAccount();
    }
}
