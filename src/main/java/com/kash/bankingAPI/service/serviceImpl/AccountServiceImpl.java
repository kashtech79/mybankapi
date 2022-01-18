package com.kash.bankingAPI.service.serviceImpl;

import com.kash.bankingAPI.entity.*;
import com.kash.bankingAPI.repository.PrimaryAccountRepository;
import com.kash.bankingAPI.repository.SavingsAccountRepository;
import com.kash.bankingAPI.service.AccountService;
import com.kash.bankingAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {

    private static int nextAccountNumber = 11111111;

    private PrimaryAccountRepository primaryAccountRepository;
    private SavingsAccountRepository savingsAccountRepository;
    private UserService userService;

    @Autowired
    public AccountServiceImpl(PrimaryAccountRepository primaryAccountRepository, SavingsAccountRepository savingsAccountRepository, UserService userService) {
        this.primaryAccountRepository = primaryAccountRepository;
        this.savingsAccountRepository = savingsAccountRepository;
        this.userService = userService;
    }

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

    @Override
    public void deposit(double amount, String accountType, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase("Primary")) {
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountRepository.save(primaryAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Deposit to Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);


        } else if (accountType.equalsIgnoreCase("Savings")) {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccountRepository.save(savingsAccount);

            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Deposit to savings Account", "Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);

        }
    }

    @Override
    public void withdraw(double amount, String accountType, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase("Primary")) {
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountRepository.save(primaryAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Withdrawn from Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);


        } else if (accountType.equalsIgnoreCase("Savings")) {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccountRepository.save(savingsAccount);

            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Withdrawn from savings Account", "Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);

        }
    }

    private int accountGen(){
        return ++nextAccountNumber;
    }

}
