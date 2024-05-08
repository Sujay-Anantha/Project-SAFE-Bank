package net.bank.safebank.employer.service;
import net.bank.safebank.employer.entity.Account;
import net.bank.safebank.employer.entity.Checking;
import net.bank.safebank.employer.entity.Loan;
import net.bank.safebank.employer.entity.Savings;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
    Account getAccountByNumber(Long accountNo);
    void createAccount(Account account);
    void updateAccount(Long accountNo, Account account);
    void deleteAccount(Long accountNo);
}