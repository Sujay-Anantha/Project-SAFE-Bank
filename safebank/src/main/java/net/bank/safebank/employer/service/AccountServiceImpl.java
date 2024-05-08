package net.bank.safebank.employer.service;

import jakarta.persistence.EntityManager;
import net.bank.safebank.employer.entity.*;
import net.bank.safebank.employer.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAllAccounts();
    }

    @Override
    public Account getAccountByNumber(Long accountNo) {
        return accountRepository.findAccountByNumber(accountNo);
    }

    @Override
    public void createAccount(Account account) {
        try {
            String dateOpened = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(account.getDateOpened());
            accountRepository.createAccount(
                    account.getAccountName(), account.getStreet(), account.getCity(),
                    account.getState(), account.getZipcode(), dateOpened,
                    account.getStatus(), account.getClass().getSimpleName(), account.getCustomerId()
            );
        } catch (Exception e) {
            logger.error("Error creating account: ", e);
        }
    }

    @Override
    public void updateAccount(Long accountNo, Account account) {
        try {
            String dateOpened = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(account.getDateOpened());
            accountRepository.updateAccount(
                    accountNo, account.getAccountName(), account.getStreet(), account.getCity(),
                    account.getState(), account.getZipcode(), dateOpened, account.getStatus()
            );
        } catch (Exception e) {
            logger.error("Error updating account: ", e);
        }
    }

    @Override
    public void deleteAccount(Long accountNo) {
        accountRepository.deleteAccount(accountNo);
    }
}



