package net.bank.safebank.employer.service;

import net.bank.safebank.employer.dto.AccountUpdateDTO;
import net.bank.safebank.employer.entity.Account;

import java.util.List;

public interface AccountService {
    public Account createAccount(Account account);
    public Account getAccountDetailsByAccountNumber(Long accountNumber);
    public List<Account> getAllAccountDetails();
    public Account updateAccountDetails(Long accountNumber, AccountUpdateDTO accountUpdateDTO);
    public void closeAccount(Long accountNumber);
}
