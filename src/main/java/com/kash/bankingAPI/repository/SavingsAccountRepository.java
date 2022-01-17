package com.kash.bankingAPI.repository;

import com.kash.bankingAPI.entity.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepository extends JpaRepository <SavingsAccount, Long> {

    SavingsAccountRepository findByAccountNumber(int accountNumber);
}
