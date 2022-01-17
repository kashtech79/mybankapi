package com.kash.bankingAPI.repository;

import com.kash.bankingAPI.entity.PrimaryAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimaryAccountRepository extends JpaRepository<PrimaryAccount, Long>{

    PrimaryAccountRepository findByAccountNumber(int accountNumber);
}
