package com.kash.bankingAPI.service.serviceImpl;

import com.kash.bankingAPI.entity.PrimaryAccount;
import com.kash.bankingAPI.entity.SavingsAccount;
import com.kash.bankingAPI.repository.PrimaryAccountRepository;
import com.kash.bankingAPI.repository.SavingsAccountRepository;
import com.kash.bankingAPI.service.AccountService;
import com.kash.bankingAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private static int nextAccountNumber = 11111111;

    @Autowired
    private PrimaryAccountRepository primaryAccountRepository;
    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

//    public AccountServiceImpl(PrimaryAccountRepository primaryAccountRepository, SavingsAccountRepository savingsAccountRepository, UserService userService) {
//        this.primaryAccountRepository = primaryAccountRepository;
//        this.savingsAccountRepository = savingsAccountRepository;
//        this.userService = userService;
//    }

    public PrimaryAccount createPrimaryAccount(){
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal(0.0));
        primaryAccount.setAccountNumber(accountGen());

        primaryAccountRepository.save(primaryAccount);

        return primaryAccount;
    }

    public SavingsAccount createSavingsAccount(){
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountBalance(new BigDecimal(0.0));
        savingsAccount.setAccountNumber(accountGen());

        savingsAccountRepository.save(savingsAccount);
        return savingsAccount;
    }

    private int accountGen(){
        return ++nextAccountNumber;
    }

}
