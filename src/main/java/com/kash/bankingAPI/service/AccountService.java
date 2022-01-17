package com.kash.bankingAPI.service;

import com.kash.bankingAPI.entity.PrimaryAccount;
import com.kash.bankingAPI.entity.SavingsAccount;

import java.security.Principal;

public interface AccountService {
    PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();

    void deposit(double amount, String accountType, Principal principal);
}
