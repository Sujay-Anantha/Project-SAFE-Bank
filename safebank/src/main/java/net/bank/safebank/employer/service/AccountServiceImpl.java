package net.bank.safebank.employer.service;

import net.bank.safebank.employer.dto.AccountUpdateDTO;
import net.bank.safebank.employer.entity.Account;
import net.bank.safebank.employer.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountRepository repository;

    @Override
    public Account createAccount(Account account) {
        Account account_saved = repository.save(account);
        return account_saved;
    }

    @Override
    public Account getAccountDetailsByAccountNumber(Long accountNumber) {
        Optional<Account> account = repository.findById(accountNumber);
        if(account.isEmpty()){
            throw new RuntimeException("Account is not present");
        }
        Account account_found = account.get();
        return account_found;
    }

    @Override
    public List<Account> getAllAccountDetails() {
        List<Account> listOfAccounts = repository.findAll();
        return listOfAccounts;
    }


    @Override
    public Account updateAccountDetails(Long acct_no, AccountUpdateDTO accountUpdateDTO) {
        Optional<Account> account = repository.findById(acct_no);
        if(account.isEmpty()){
            throw new RuntimeException("Account is not present");
        }
        Account account_found = account.get();
        // Update only the allowed fields
        account_found.setAcct_name(accountUpdateDTO.getAcct_name());
        account_found.setAstreet(accountUpdateDTO.getAstreet());
        account_found.setAcity(accountUpdateDTO.getAcity());
        account_found.setAstate(accountUpdateDTO.getAstate());
        account_found.setAzipcode(accountUpdateDTO.getAzipcode());
        account_found.setAstatus(accountUpdateDTO.getAstatus());
        account_found.setAcct_type(accountUpdateDTO.getAcct_type());

        return repository.save(account_found);
    }

    @Override
    public void closeAccount(Long accountNumber) {
        getAccountDetailsByAccountNumber(accountNumber);
        repository.deleteById(accountNumber);
    }
}


