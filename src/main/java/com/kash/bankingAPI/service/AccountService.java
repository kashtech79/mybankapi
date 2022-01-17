package com.kash.bankingAPI.service;

import com.kash.bankingAPI.entity.PrimaryAccount;
import com.kash.bankingAPI.entity.SavingsAccount;

public interface AccountService {
    PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();
}
